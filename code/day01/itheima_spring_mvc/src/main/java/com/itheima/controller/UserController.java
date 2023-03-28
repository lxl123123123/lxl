package com.itheima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.domain.Vo;
import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


@Controller
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/quick")
    public String save1(){
        userService.save();
        System.out.println("Controller save running....");
        return "success.jsp";
    }
    @RequestMapping("/quick2")
    public ModelAndView save2(){
        /*
        Model:模型 作用封装数据
        View: 视图 作用展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        //设置视图名称
        modelAndView.setViewName("success.jsp");
        //设置模型数据
        modelAndView.addObject("username","itcast");
        return modelAndView;
    }

    @RequestMapping("/quick3")
    public String save3(HttpServletRequest req){
        ServletContext servletContext = req.getServletContext();
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = app.getBean(UserService.class);
        userService.save();
        return "success.jsp";
    }

    @RequestMapping("/quick4")
    public ModelAndView save4(HttpServletRequest req,ModelAndView modelAndView){
        ServletContext servletContext = req.getServletContext();
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = app.getBean(UserService.class);
        userService.save();
        modelAndView.addObject("username","酷丁鱼");
        modelAndView.setViewName("success.jsp");
        return modelAndView;
    }

    @RequestMapping("/quick5")
    public ModelAndView save5(HttpServletRequest req){
        ServletContext servletContext = req.getServletContext();
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = app.getBean(UserService.class);
        userService.save();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username","酷丁鱼");
        modelAndView.setViewName("success.jsp");
        return modelAndView;
    }

    @RequestMapping("/quick6")
    public void save6(HttpServletResponse resp) throws IOException {
        resp.getWriter().print("hello world");
    }

    @RequestMapping("/quick7")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public String save7(){
        return "hello itHeiMa";
    }

    @RequestMapping("/quick8")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public String save8(){
        return "{\"username\":\"zhangSan\",\"age\":18}";
    }

    @RequestMapping("/quick9")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public String save9() throws JsonProcessingException {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(30);
        //使用json转换工具将对象转换成json格式字符串再返回
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(user);
        return s;
    }

    @RequestMapping("/quick10")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public User save10() throws JsonProcessingException {
        User user = new User();
        user.setUsername("lisi2");
        user.setAge(32);
        return user;
    }

    @RequestMapping("/quick11")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public void save11(String username,int age) throws JsonProcessingException {
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping("/quick12")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public void save12(User user) throws JsonProcessingException {
        System.out.println(user);
    }

    @RequestMapping("/quick13")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public void save13(String[] strs) throws JsonProcessingException {
        System.out.println(Arrays.asList(strs));
    }

    @RequestMapping("/quick14")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public void save14(Vo vo) throws JsonProcessingException {
        System.out.println(vo);
    }

    @RequestMapping("/quick15")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public void save15(@RequestParam(value = "name",required = false,defaultValue = "itcast") String username) throws JsonProcessingException {
        System.out.println(username);
    }

    @RequestMapping("/quick16")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public void save16(String username, MultipartFile uploadFile,MultipartFile uploadFile2) throws IOException {
        System.out.println(username);
        //获得上传文件名称
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("D:\\upload\\"+originalFilename));
        String originalFilename2 = uploadFile2.getOriginalFilename();
        uploadFile.transferTo(new File("D:\\upload\\"+originalFilename2));
    }
}
