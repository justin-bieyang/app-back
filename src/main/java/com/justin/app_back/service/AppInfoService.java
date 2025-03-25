package com.justin.app_back.service;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.AppInfo;
import org.aopalliance.intercept.Interceptor;
import org.springframework.stereotype.Service;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public interface AppInfoService {

    PageInfo getPage(AppInfo appInfo, int pageNum);

    int update(AppInfo appInfo);

    int deleteById(Integer id, String uploadPath);

    boolean validateAPKName(String apkname, Integer id);

    AppInfo getById(Integer id);

    AppInfo getAppWithVersion(Integer appid);
}

