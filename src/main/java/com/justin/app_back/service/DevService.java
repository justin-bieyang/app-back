package com.justin.app_back.service;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.DevUser;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface DevService {
    PageInfo geyPage(DevUser devUser, int pageNum);

    void updateDev(DevUser devUser,Integer adminOrDevId,String userTye);

    void addDev(DevUser devUser, Integer adminId);

    void deleteDev(Integer id);
}
