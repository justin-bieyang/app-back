package com.justin.app_back.mapper;

import com.justin.app_back.pojo.AppInfo;

import java.util.List;

/**
* @author ducao
* @description 针对表【app_info】的数据库操作Mapper
* @createDate 2025-03-06 22:14:44
* @Entity pojo.domain.AppInfo
*/
public interface AppInfoMapper {

    int deleteByPrimaryKey(int id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    List<AppInfo> selectBy(AppInfo appInfo);
}
