package com.justin.app_back.mapper;

import com.justin.app_back.pojo.AppCategory;

import java.util.List;

/**
* @author ducao
* @description 针对表【app_category】的数据库操作Mapper
* @createDate 2025-03-06 22:14:44
* @Entity pojo.domain.AppCategory
*/
public interface AppCategoryMapper {

    int deleteByPrimaryKey(int id);

    int insert(AppCategory record);

    int insertSelective(AppCategory record);

    AppCategory selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(AppCategory record);

    int updateByPrimaryKey(AppCategory record);

    List<AppCategory> selectAll();
}
