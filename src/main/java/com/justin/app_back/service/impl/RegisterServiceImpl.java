package com.justin.app_back.service.impl;

import com.justin.app_back.dto.RegisterDto;
import com.justin.app_back.mapper.BackendUserMapper;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private BackendUserMapper backendUserMapper;

    @Override
    public void registerCommonUser(RegisterDto registerDto) {
        BackendUser backendUser = backendUserMapper.selectByCommonUsername(registerDto.getUsername());
        if (backendUser != null) {
            throw new RuntimeException("该用户名已存在");
        }
        backendUser = new BackendUser();
        backendUser.setCreatedate(new Date());
        backendUser.setUsername(registerDto.getUsername());
        backendUser.setUserpassword(registerDto.getPassword());
        backendUser.setUsercode("user");
        backendUser.setUsertype(2);
        backendUserMapper.insertSelective(backendUser);
    }
}
