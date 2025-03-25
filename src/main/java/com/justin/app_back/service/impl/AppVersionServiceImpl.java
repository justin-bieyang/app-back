package com.justin.app_back.service.impl;

import com.justin.app_back.mapper.AppVersionMapper;
import com.justin.app_back.pojo.AppVersion;
import com.justin.app_back.service.AppVersionServion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Service
public class AppVersionServiceImpl implements AppVersionServion {

    @Resource
    private AppVersionMapper appVersionMapper;

    @Override
    public void addApk(AppVersion appVersion) {
        appVersionMapper.insertSelective(appVersion);
    }
}
