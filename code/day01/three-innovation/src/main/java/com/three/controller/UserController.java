package com.three.controller;

import com.three.dto.LoginFormDTO;
import com.three.dto.Result;
import com.three.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @GetMapping("/")
    public String index(){
        return "success";
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("/code")
    public Result sendCode(@RequestParam("phone") String phone) {

        // TODO 发送短信验证码并保存验证码
        System.out.println("aaa");
        return userService.sendCode(phone);
    }
    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号、验证码；或者手机号、密码
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm){
        // TODO 实现登录功能
        return userService.login(loginForm);
    }
    /**
     * A发起请求 携带参数 我将得到的参数保存到redis中 之后会有另一个方法请求从redis中取出保存的数据 key是phone value是roomId
     * @Param phone B的手机号 roomId A的roomID(房间号)
     */
    @PostMapping("/save")
    public Result saveDataToRedis(@RequestParam("phone") String phone,@RequestParam("roomId") String roomId){
        // TODO 实现保存信息到redis
        System.out.println("aaa");
        return userService.saveDataToRedis(phone,roomId);
    }
    /**
     * 请求redis中之前保存的数据 通过B的phone作为key去redis中找到相应的value(roomId)
     * @Param phone B的手机号
     */
    @PostMapping("/select")
    public Result selectRoomIdWithPhone(@RequestParam("phone") String phone){
        // TODO 实现保存信息到redis
        return userService.selectRoomIdWithPhone(phone);
    }
}
