package com.justin.app_back.mapper;

import com.justin.app_back.pojo.AppVersion;

import java.util.List;

/**
* @author ducao
* @description 针对表【app_version】的数据库操作Mapper
* @createDate 2025-03-06 22:14:44
* @Entity pojo.domain.AppVersion
*/
public interface AppVersionMapper {

    int deleteByPrimaryKey(int id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    List<AppVersion> selectByAppId(Integer id);
}
