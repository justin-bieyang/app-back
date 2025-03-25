package com.justin.app_back.mapper;

import com.justin.app_back.pojo.BackendUser;

import java.util.List;

/**
* @author ducao
* @description 针对表【backend_user】的数据库操作Mapper
* @createDate 2025-03-06 22:14:44
* @Entity pojo.domain.BackendUser
*/
public interface BackendUserMapper {

    int deleteByPrimaryKey(int id);

    int insert(BackendUser record);

    int insertSelective(BackendUser record);

    BackendUser selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(BackendUser record);

    int updateByPrimaryKey(BackendUser record);

    BackendUser selectByAdminUsername(String username);

    List<BackendUser> selectBy(BackendUser backendUser);

    BackendUser selectByCommonUsername(String username);
}
