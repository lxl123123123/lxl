package com.itheima.controller;

import com.itheima.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Rest模式
@Controller
@RequestMapping("/books")
public class BookController {

    @Value("${country}")
    private String i;

    @Value("${user1.name}")
    private String username;

    @Value("${user1.age}")
    private int j;

    @Value("${a.b.c.d.e}")
    private Integer k;

    @Value("${likes[1]}")
    private String like1;

    @Value("${user2[0].age}")
    private String user0;

    @Autowired
    private Environment environment;

    @Autowired
    private User user;


    @RequestMapping("/s")
    @ResponseBody
    public String getBuyId(){
        System.out.println("springboot is running...");
        System.out.println(i);
        System.out.println(username);
        System.out.println(j);
        System.out.println(k);
        System.out.println(like1);
        System.out.println(user0);
        System.out.println(environment.getProperty("country"));
        System.out.println(environment.getProperty("user2[0].age"));
        System.out.println(environment.getProperty("user.name"));
        System.out.println("-------------------");
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getAge());
        return "springboot is running...";
    }

}
