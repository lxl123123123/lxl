package com.three.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.three.domain.User;
import com.three.dto.LoginFormDTO;
import com.three.dto.Result;

public interface IUserService extends IService<User> {
    Result sendCode(String phone);

    Result login(LoginFormDTO loginForm);

    Result saveDataToRedis(String phone, String roomId);

    Result selectRoomIdWithPhone(String phone);
}
