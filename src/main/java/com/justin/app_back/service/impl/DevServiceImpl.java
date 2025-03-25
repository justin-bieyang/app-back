package com.justin.app_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justin.app_back.mapper.DevUserMapper;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.pojo.DevUser;
import com.justin.app_back.service.DevService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Service
public class DevServiceImpl implements DevService {

    @Resource
    private DevUserMapper devUserMapper;

    @Override
    public PageInfo geyPage(DevUser devUser, int pageNum) {

        PageHelper.startPage(pageNum, 5, "id asc");
        List<BackendUser> backendUserList = devUserMapper.selectBy(devUser);
        return new PageInfo(backendUserList);
    }
}
