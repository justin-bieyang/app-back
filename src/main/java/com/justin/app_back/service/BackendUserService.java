package com.justin.app_back.service;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.BackendUser;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface BackendUserService {
    PageInfo getPage(BackendUser backendUser, int pageNum);

    void deleteById(Integer id);

    void updateUserType(BackendUser backendUser, Integer adminOrUserId, String userType);
}
