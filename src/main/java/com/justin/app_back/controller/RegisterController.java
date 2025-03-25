package com.justin.app_back.controller;

import com.justin.app_back.dto.RegisterDto;
import com.justin.app_back.service.RegisterService;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @PostMapping("/register")
    public ResultVo register(@RequestBody RegisterDto registerDto) {

        registerService.registerCommonUser(registerDto);

        return ResultVo.success("注册成功", null);
    }

}
