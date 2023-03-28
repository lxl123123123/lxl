package com.three.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.three.dao.UserDao;
import com.three.domain.User;
import com.three.dto.LoginFormDTO;
import com.three.dto.Result;
import com.three.dto.UserDTO;
import com.three.service.IUserService;
import com.three.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.three.utils.RedisConstants.*;
import static com.three.utils.SystemConstants.*;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements IUserService{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone) {
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
        log.debug("发送短信验证码成功，验证码：{}",stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone));
//        System.out.printf("发送短信验证码成功，验证码：{}",code);
        //6.返回ok
        return Result.ok(code);
    }

    @Override
    public Result login(LoginFormDTO loginForm) {
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
//        userMap.put("roomId",userDTO.getRoomId());
//        userMap.put("streamId",userDTO.getStreamId());
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

    @Override
    public Result saveDataToRedis(String phone, String roomId) {
        stringRedisTemplate.opsForValue().set(SAVE_DATA_KEY + phone,roomId);
        return Result.ok("success");
    }

    @Override
    public Result selectRoomIdWithPhone(String phone) {
        String s = stringRedisTemplate.opsForValue().get(SAVE_DATA_KEY + phone);
        stringRedisTemplate.delete(SAVE_DATA_KEY + phone);
        if (s == null || s.length() == 0){
            return Result.ok("没有查找到符合的房间号");
        }
        return Result.ok(s);
    }

    private User createUserWithPhone(String phone) {
        //创建用户
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
//        user.setRoomId(USER_ROOM_ID_PREFIX + RandomUtil.randomNumbers(6));
//        user.setStreamId(USER_STREAM_ID_PREFIX + RandomUtil.randomNumbers(6));
        //保存用户到数据库
        save(user);
        //返回用户
        return user;
    }
}
