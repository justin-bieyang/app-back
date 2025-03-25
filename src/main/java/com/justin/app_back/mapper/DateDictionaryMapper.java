package com.justin.app_back.mapper;

import com.justin.app_back.pojo.DateDictionary;

/**
* @author ducao
* @description 针对表【date_dictionary】的数据库操作Mapper
* @createDate 2025-03-06 22:14:44
* @Entity pojo.domain.DateDictionary
*/
public interface DateDictionaryMapper {

    int deleteByPrimaryKey(int id);

    int insert(DateDictionary record);

    int insertSelective(DateDictionary record);

    DateDictionary selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(DateDictionary record);

    int updateByPrimaryKey(DateDictionary record);

}
