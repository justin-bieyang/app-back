package com.justin.app_back.mapper;

import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface UserAppFkMapper {

    @Delete("delete from user_app_fk where userId = #{userId} and appId = #{appId}")
    int delete(@Param("appId") Integer appId, @Param("userId") Integer userId);
}
