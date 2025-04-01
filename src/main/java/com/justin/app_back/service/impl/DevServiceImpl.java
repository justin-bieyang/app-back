package com.justin.app_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justin.app_back.mapper.DevUserMapper;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.pojo.DevUser;
import com.justin.app_back.service.DevService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        List<DevUser> devUsers = devUserMapper.selectBy(devUser);
        return new PageInfo(devUsers);
    }

    @Override
    public void updateDev(DevUser devUser, Integer adminOrDevId, String userType) {

        if(userType.equals("admin")){
            devUser.setModifyby(adminOrDevId);

            devUser.setModifydate((new Date()));

            devUserMapper.updateByPrimaryKeySelective(devUser);
        } else if (userType.equals("dev")) {
            if(!Objects.equals(adminOrDevId, devUser.getId())){
                throw new RuntimeException("你只能修改本人的信息");
            } else if (Objects.equals(adminOrDevId, devUser.getId())) {
                devUser.setModifyby(adminOrDevId);

                devUser.setModifydate((new Date()));

                devUserMapper.updateByPrimaryKeySelective(devUser);
            }
        }
    }

    @Override
    public void addDev(DevUser devUser, Integer adminId) {
        devUser.setCreatedby(adminId);
        devUserMapper.insert(devUser);
    }

    @Override
    public void deleteDev(Integer id) {
        devUserMapper.deleteByPrimaryKey(id);
    }
}
