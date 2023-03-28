package com.heima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        //写入一条string数据
        stringRedisTemplate.opsForValue().set("name","虎哥1");
        //获取string数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = "+ name);
    }
    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    void testSaveUser() throws JsonProcessingException {
        User user = new User("虎哥",21);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入一条string数据
        stringRedisTemplate.opsForValue().set("user:200",json);
        //获取string数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        //手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user1 = "+ user1.toString());
    }
    @Test
    void testHash() {
        //写入一条string数据
        stringRedisTemplate.opsForHash().put("user:300","name","刚子");
        stringRedisTemplate.opsForHash().put("user:300","age","19");
        //获取string数据
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:300");
        Set<Object> keys = stringRedisTemplate.opsForHash().keys("user:300");
        List<Object> values = stringRedisTemplate.opsForHash().values("user:300");
        System.out.println("entries = "+ entries);
        System.out.println(keys);
        System.out.println(values);
    }

}
