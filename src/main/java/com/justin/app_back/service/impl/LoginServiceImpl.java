package com.justin.app_back.service.impl;

import com.justin.app_back.dto.LoginDto;
import com.justin.app_back.mapper.BackendUserMapper;
import com.justin.app_back.mapper.DevUserMapper;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.pojo.DevUser;
import com.justin.app_back.service.LoginService;
import com.justin.app_back.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private BackendUserMapper backendUserMapper;

    @Resource
    private DevUserMapper devUserMapper;

    @Override
    public String login(LoginDto loginDto) {
        if (loginDto.getUserType().equals("admin")) {
            // 判断账户密码
            BackendUser backendUser = backendUserMapper.selectByAdminUsername(loginDto.getUsername());
            if (backendUser == null) {
                throw new RuntimeException("用户不存在");
            }
            if (!loginDto.getPassword().equals(backendUser.getUserpassword())){
                throw new RuntimeException("密码错误");
            }

            // 账户和密码都正确，就要给令牌

            return JwtUtil.getToken(loginDto.getUsername());

        }else if (loginDto.getUserType().equals("dev")) {
            // 判断账户密码
            DevUser devUser= devUserMapper.selectByUsername(loginDto.getUsername());
            if (devUser == null) {
                throw new RuntimeException("用户不存在");
            }
            if (!loginDto.getPassword().equals(devUser.getDevpassword())){
                throw new RuntimeException("密码错误");
            }

            // 账户和密码都正确，就要给令牌

            return JwtUtil.getToken(loginDto.getUsername());
        } else if (loginDto.getUserType().equals("user")) {

            // 判断账户密码
            BackendUser backendUser = backendUserMapper.selectByCommonUsername(loginDto.getUsername());
            if (backendUser == null) {
                throw new RuntimeException("用户不存在");
            }
            if (!loginDto.getPassword().equals(backendUser.getUserpassword())){
                throw new RuntimeException("密码错误");
            }

            // 账户和密码都正确，就要给令牌

            return JwtUtil.getToken(loginDto.getUsername());

        } else {
            throw new RuntimeException("非法操作");
        }
    }
}
