package com.justin.app_back.controller;


import com.justin.app_back.dto.DevRegisterDto;
import com.justin.app_back.dto.UserRegisterDto;
import com.justin.app_back.service.RegisterService;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @PostMapping("/user")
    public ResultVo registerUser(@RequestBody UserRegisterDto registerDto) {

        registerService.registerCommonUser(registerDto);

        return ResultVo.success("普通用户注册成功", null);
    }

    @PostMapping("/dev")
    public ResultVo registerDev(@RequestBody DevRegisterDto registerDto) {

        registerService.registerDev(registerDto);

        return ResultVo.success("开发者注册成功", null);
    }

}
