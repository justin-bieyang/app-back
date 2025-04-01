package com.justin.app_back.service.impl;

import com.justin.app_back.dto.DevRegisterDto;
import com.justin.app_back.dto.UserRegisterDto;
import com.justin.app_back.mapper.BackendUserMapper;
import com.justin.app_back.mapper.DevUserMapper;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.pojo.DevUser;
import com.justin.app_back.service.RegisterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private DevUserMapper devUserMapper;

    @Override
    public void registerCommonUser(UserRegisterDto registerDto) {
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void registerDev(DevRegisterDto registerDto) {
        DevUser devUser = devUserMapper.selectByUsername(registerDto.getDevName());

        if (devUser != null) {
            throw new RuntimeException("该开发者称号已存在！");
        }
        devUser = new DevUser();
        devUser.setCreateddate(new Date());
        devUser.setDevname(registerDto.getDevName());
        devUser.setDevpassword(registerDto.getDevPassword());
        devUser.setDevemail(registerDto.getDevEmail());
        devUser.setDevinfo(registerDto.getDevInfo());
        devUserMapper.insertSelective(devUser);
        DevUser devUser1 = devUserMapper.selectByUsername(registerDto.getDevName());
        Integer id = devUser1.getId();
        devUser1.setCreatedby(id);
        devUser1.setDevcode("DEV_" + id);
        devUserMapper.updateByPrimaryKeySelective(devUser1);
    }
}
