package com.itheima.controller;

import com.itheima.config.Limit;
import com.itheima.controller.utils.R;
import com.itheima.domain.User;
import com.itheima.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/User")
public class userController {

    HttpSession session1 =null;
    HttpSession session2 =null;
    int flag = 0;

    @Autowired
    private userService userService;


    public void setUserService(com.itheima.service.userService userService) {
        this.userService = userService;
    }

    //前端输入手机号，但前端不会直接给我一个手机号，这样容易暴露用户信息，所以他会给我一个User对象
    //用户点击获取验证码按钮时会调用该方法 后台返回code给前端用户
    @PostMapping("/getCode")
    @Limit(key = "limit1", permitsPerSecond = 2, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public R getCode(User user){
        Object code = userService.sendCodeToSMS(user.getPhone());
        return new R(true,code);
    }

    //通过用户的手机号和后台返回给他的验证码实现验证登录，检查验证码是否正确
    //当用户输完验证码后，点击登录时，调用该请求
    @PostMapping("/login")
    @Limit(key = "limit2", permitsPerSecond = 2, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public R checkCodeAndLogin(User user,HttpServletRequest request){

        if (userService.selectByPhone(user.getPhone()) == null){ //说明没查到，即这个人没有注册过
            if (userService.checkCode(user) == true){ //再看验证码对不对，对的话说明登录成功让他去注册页面，并返回给前端数据1：第一次登
                return new R(true,1);
            }
        }else if (userService.checkCode(user) == true){ //说明查到了，即这个人在数据库中有数据，即他已经注册过了，再看验证码对不对，对的话说明登录成功让他去查询面试结果，并返回给前端数据2：第二次及之后登
            session2 = request.getSession();
            session2.setAttribute("phone2", user.getPhone());
            return new R(true,2);
        }

        return new R(false);

    }

    @PostMapping("/save")
    @Limit(key = "limit3", permitsPerSecond = 2, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public R save(@RequestBody User user, HttpServletRequest request){
        session1 = request.getSession();
        session1.setAttribute("phone1", user.getPhone());
        flag=1;
        return new R(userService.save(user));
    }

    @GetMapping("/getState")
    @Limit(key = "limit4", permitsPerSecond = 2, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public R getState(){
        User user1 = null;
        String phone = null;
        if (flag==1){
            phone = (String) session1.getAttribute("phone1");
            user1 = userService.getState(phone);
        }else {
            phone = (String) session2.getAttribute("phone2");
            user1 = userService.getState(phone);
        }
        return new R(true,user1.getState());
    }

    @GetMapping("/a")
    @Limit(key = "limit5", permitsPerSecond = 2, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public String a(){
        return "ok";
    }

//    @PostMapping("/login")
//    public R login( User user, HttpSession session){
//
//        User user1 = userService.login(user.getUsername(),user.getPassword());
//        if (user1 != null){
//            //登录成功,将user存到session中
//            session.setAttribute("user",user1);
//            return new R(true,"登录成功^_^");
//        }else {
//            return new R(false,"登录失败-_-!");
//        }
//    }


//    @DeleteMapping("/{id}")
//    public R delete(@PathVariable Integer id){
//        return new R(userService.removeById(id));
//    }
//
//    @PutMapping
//    public R update(@RequestBody User user){
//        return new R(userService.updateById(user));
//    }
//
//    @GetMapping("/{id}")
//    public R getById(@PathVariable Integer id){
//        return new R(true,userService.getById(id));
//    }
//
//    @GetMapping
//    public R getAll(){
//        return new R(true,userService.list());
//    }

//    @GetMapping("/{currentPage}/{pageSize}")
//    public R page(@PathVariable Integer currentPage,@PathVariable Integer pageSize,User user){
//        IPage<User> page = userService.getPage(currentPage, pageSize, user);
//        //如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
//        if (currentPage > page.getPages()){
//            page = userService.getPage((int)page.getPages(),pageSize,user);
//        }
//
//        return new R(true,page);
//
//    }

}
