package com.justin.app_back.service;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.AppInfo;
import com.justin.app_back.pojo.UsersApp;
import org.aopalliance.intercept.Interceptor;
import org.springframework.stereotype.Service;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface AppInfoService {

    PageInfo getPage(AppInfo appInfo, int pageNum);

//    int update(AppInfo appInfo, Integer adminOrDevId);
    int update(AppInfo appInfo, String useName);

    int deleteById(Integer id, String uploadPath);

    boolean validateAPKName(String apkname, Integer id);

    AppInfo getById(Integer id);

    AppInfo getAppWithVersion(Integer appid);

    void reviewAppStatus(AppInfo appInfo, String adminUsername, Integer statusId);

    void collectApp(UsersApp usersApp, String username);

    PageInfo getCollectPage(AppInfo appInfo, int pageNum, String username);

    void cancelCollect(Integer appId, String username);
}

