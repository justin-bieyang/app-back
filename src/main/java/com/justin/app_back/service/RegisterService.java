package com.justin.app_back.service;


import com.justin.app_back.dto.DevRegisterDto;
import com.justin.app_back.dto.UserRegisterDto;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface RegisterService {

    void registerCommonUser(UserRegisterDto registerDto);

    void registerDev(DevRegisterDto registerDto);
}
