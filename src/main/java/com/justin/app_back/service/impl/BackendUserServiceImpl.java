package com.justin.app_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justin.app_back.mapper.BackendUserMapper;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.service.BackendUserService;
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
public class BackendUserServiceImpl implements BackendUserService {

    @Resource
    private BackendUserMapper backendUserMapper;

    @Override
    public PageInfo getPage(BackendUser backendUser, int pageNum) {

        PageHelper.startPage(pageNum, 5, "id asc");
        List<BackendUser> backendUserList = backendUserMapper.selectBy(backendUser);
        return new PageInfo(backendUserList);
    }

    @Override
    public void deleteById(Integer id) {
        backendUserMapper.deleteByPrimaryKey(id);
    }

//    @Override
////    public void updateUserType(BackendUser backendUser, Integer adminOrUserId, String userType) {
////
////        if (userType.equals("admin")) {
////            backendUser.setModifyby(adminOrUserId);
////            backendUser.setModifydate(new Date());
////            backendUserMapper.updateByPrimaryKeySelective(backendUser);
////        } else if (userType.equals("user")) {
////            if (!Objects.equals(adminOrUserId, backendUser.getId())) {
////                throw new RuntimeException("你只能修改本人信息");
////            } else if (Objects.equals(adminOrUserId, backendUser.getId())) {
////                backendUser.setModifyby(adminOrUserId);
////                backendUser.setModifydate(new Date());
////                backendUserMapper.updateByPrimaryKeySelective(backendUser);
////            }
////        }
////    }
@Override
public void updateUserType(BackendUser backendUser, String username) {

    BackendUser backendUser1 = backendUserMapper.selectByUsername(username);
    Integer adminOrUserId = backendUser1.getId();
    Integer userType = backendUser1.getUsertype();

    if (userType == 1) {
        backendUser.setModifyby(adminOrUserId);
        backendUser.setModifydate(new Date());
        backendUserMapper.updateByPrimaryKeySelective(backendUser);
    } else if (userType == 2) {
        if (!Objects.equals(adminOrUserId, backendUser.getId())) {
            throw new RuntimeException("你只能修改本人信息");
        } else if (Objects.equals(adminOrUserId, backendUser.getId())) {
            backendUser.setModifyby(adminOrUserId);
            backendUser.setModifydate(new Date());
            backendUserMapper.updateByPrimaryKeySelective(backendUser);
        }
    }
}
}
