package com.justin.app_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justin.app_back.mapper.BackendUserMapper;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.service.BackendUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public void updateUserType(BackendUser backendUser) {
        backendUserMapper.updateByPrimaryKeySelective(backendUser);
    }
}
