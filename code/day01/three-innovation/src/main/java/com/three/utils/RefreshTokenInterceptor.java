package com.three.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.three.dto.UserDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.three.utils.RedisConstants.LOGIN_USER_KEY;
import static com.three.utils.RedisConstants.LOGIN_USER_TTL;

//拦截器进行登录验证(经典加一层)  拦截全部
//目的：如果用户一直点的都是非拦截器的请求 那么他就不会动态刷新token过期时间 等于说用户一直有动作 只是动作都是非拦截的
//比如 首页 登录 查看其他的一些 类似于这些的操作 这样进行访问30分钟 token也会过期 这样显然不符合大众预期的效果
//也会带来不好的用户体验
//解决办法：加一层 这层拦截器拦截全部 使得所有的请求都会先通过这个拦截器 然后再进入下一个拦截器 这个拦截器的作用是动态更新
//token时长和进行一些其他动作  而下一个拦截器的作用才是真正的拦截工作
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取session          (修改后)
//        HttpSession session = request.getSession();
        //1.获取请求头中的token      (修改前)
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)){
            //直接放行 在下一个拦截器进行拦截
            return true;
        }
//        //2.从session中获取用户    (修改前)
//        Object user = session.getAttribute("user");
        //2.基于token获取redis中的用户  (修改后)
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
        //3.判断用户是否存在
        if (userMap.isEmpty()){
            //同上
            return true;
        }
        //5.将查询到的Hash数据转为userDTO对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        //6.存在，保存用户信息到ThreadLocal中
        UserHolder.saveUser(userDTO);
        //7.刷新token有效期
        stringRedisTemplate.expire(LOGIN_USER_KEY + token,LOGIN_USER_TTL, TimeUnit.MINUTES);
        //8.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
