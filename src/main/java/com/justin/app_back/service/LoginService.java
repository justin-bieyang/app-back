package com.justin.app_back.service;

import com.justin.app_back.dto.LoginDto;
import com.justin.app_back.vo.LoginVo;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface LoginService {
    LoginVo login(LoginDto loginDto);
}
