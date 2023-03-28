package com.heima.test;

import com.heima.jedis.util.JedisConnectionFactory;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        //1.建立连接
//        jedis = new Jedis("127.0.0.1",6379);
        jedis = JedisConnectionFactory.getJedis();
        //3.选择库
        jedis.select(0);
    }

    @Test
    void testHash() {
        jedis.hset("user:1","name","jack");
        jedis.hset("user:1","age","21");
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @Test
    void testString() {
        String result = jedis.set("name", "jack1");
        System.out.println("result = " + result);
        //获取数据
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null){
            jedis.close();
        }
    }
}
