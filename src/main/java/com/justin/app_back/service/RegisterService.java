package com.justin.app_back.service;

import com.justin.app_back.dto.RegisterDto;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface RegisterService {

    void registerCommonUser(RegisterDto registerDto);
}
