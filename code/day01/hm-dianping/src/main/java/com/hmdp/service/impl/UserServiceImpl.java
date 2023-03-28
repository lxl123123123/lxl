package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RegexUtils;
import com.hmdp.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone, HttpSession session) {
        //1.校验手机号
        if (RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合 返回错误信息
            return Result.fail("手机号格式错误");
        }
        //3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);

//        //4.保存验证码到session                    (修改前 用session存储 问题：session共享问题)
//        session.setAttribute("code",code);
        //4.保存验证码到Redis                         (修改后 用Redis存储)
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone,code,LOGIN_CODE_TTL, TimeUnit.MINUTES);
        //5.发送验证码
        log.debug("发送短信验证码成功，验证码：{}",code);
        //6.返回ok
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        //1.校验手机号
        String phone = loginForm.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合 返回错误信息
            return Result.fail("手机号格式错误");
        }
//        //2.校验验证码          (修改前 从session中取)
//        Object cacheCode = session.getAttribute("code");
        //2.校验验证码        (修改后 从Redis中取)
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        String code = loginForm.getCode();
        if (cacheCode == null || !cacheCode.equals(code)){
            //3.不一致，报错
            Result.fail("验证码错误");
        }

        //4.一致，根据手机号查询用户
        User user = query().eq("phone", phone).one();
        //5.判断用户是否存在
        if (user == null){
            //6.不存在，创建新用户并返回
            user = createUserWithPhone(phone);
        }

//        //7.存在 保存用户到session中         (修改前)
//        session.setAttribute("user", BeanUtil.copyProperties(user, UserDTO.class));
        //7.存在 保存用户到Redis中         (修改后)
        //7.1.随机生成token，作为登录令牌 也就是存入Redis中的key
        String token = UUID.randomUUID().toString(true);
        //7.2.将User对象转为HashMap存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String,String> userMap = new HashMap();
        userMap.put("id",userDTO.getId().toString());
        userMap.put("nickName",userDTO.getNickName());
        userMap.put("icon",userDTO.getIcon());
        //7.3.存储
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token,userMap);
        //7.4.设置token有效期
        stringRedisTemplate.expire(LOGIN_USER_KEY + token,LOGIN_USER_TTL,TimeUnit.MINUTES);
        //8.返回token给客户端 以便于在第二次访问浏览器时 客户端携带token找到刚才存入的user数据
        //(客户端携带token) 解释：前端设置了一个拦截器 客户端会在每一次前端即将访问后端请求时拦截器会拦截住
        //在上面绑定一个token信息 后端才会让你拿着这个token去访问对应的redis数据
        //之前用session时 是存入session时 每个session会对应唯一的SessionId交给cookie 第二次访问浏览器时
        //客户端会拿着cookie和唯一的SessionId来查找到对应的数据
        //修改前
//        return Result.ok();
        //修改后
        return Result.ok(token);
    }

    //用户签到功能
    @Override
    public Result sign() {
        //1.获取当前用户id
        Long userId = UserHolder.getUser().getId();
        //2.获取日期
        LocalDateTime now = LocalDateTime.now();
        //3.拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        //4.获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        //5.写入redis SETBIT key offset 1  这里就是签到功能 所以写true 没签不用管 默认是0/false
        stringRedisTemplate.opsForValue().setBit(key,dayOfMonth-1,true);
        return Result.ok();
    }

    //统计连续签到(规定连续：从最后一位开始 往前面遍历 直到遇到第一个不为1的为止)
    @Override
    public Result signCount() {
        //1获取当前用户id
        Long userId = UserHolder.getUser().getId();
        //2.获取日期
        LocalDateTime now = LocalDateTime.now();
        //3.拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        //4.获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        //5.获取本月截止今天为止的所有签到记录 拿到的是一个十进制的数字 BITFIELD sign:5:202212 GET u14 0
        List<Long> result = stringRedisTemplate.opsForValue().bitField(
                key,
                BitFieldSubCommands.create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0)
        );
        if (result == null || result.isEmpty()){
            //没有任何签到结果
            return Result.ok(Collections.emptyList());
        }
        Long num = result.get(0);
        if (num == null || num == 0){
            return Result.ok(0);
        }
        //6.循环遍历
        int count = 0; //计数器
        while (true) {
            //6.1让这个数字与1做与运算 得到数字的最后一个bit位 判断这个bit位是否为0
            if ((num & 1) == 0) {
                //6.2如果为0 说明未签到 结束
                break;
            }else {
                //6.3如果不为0 说明已签到 计数器+1
                count++;
            }
            //6.4把数字右移一位 抛弃最后一个bit位 继续下一个bit位
            num >>>= 1;
        }
        return Result.ok(count);
    }

    private User createUserWithPhone(String phone) {
        //创建用户
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        //保存用户到数据库
        save(user);
        //返回用户
        return user;
    }
}
