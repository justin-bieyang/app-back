package com.justin.app_back.controller;

import com.justin.app_back.dto.LoginDto;
import com.justin.app_back.service.LoginService;
import com.justin.app_back.vo.LoginVo;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginDto loginDto) {

//        if(httpSession.getAttribute("login") != null) {
//            return ResultVo.success("登录成功","okay");
//        }


        LoginVo login = loginService.login(loginDto);

//       httpSession.setAttribute("login","jack");

        return ResultVo.success("登录成功",login);

    }

}
