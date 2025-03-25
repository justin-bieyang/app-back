package com.justin.app_back.mapper;

import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.pojo.DevUser;

import java.util.List;

/**
* @author ducao
* @description 针对表【dev_user】的数据库操作Mapper
* @createDate 2025-03-06 22:14:44
* @Entity pojo.domain.DevUser
*/
public interface DevUserMapper {

    DevUser selectByUsername(String username);

    int deleteByPrimaryKey(int id);

    int insert(DevUser record);

    int insertSelective(DevUser record);

    DevUser selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(DevUser record);

    int updateByPrimaryKey(DevUser record);

    List<BackendUser> selectBy(DevUser devUser);
}
