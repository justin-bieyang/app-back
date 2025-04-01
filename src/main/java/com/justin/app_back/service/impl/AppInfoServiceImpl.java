package com.justin.app_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justin.app_back.mapper.AppInfoMapper;
import com.justin.app_back.mapper.AppVersionMapper;
import com.justin.app_back.mapper.UserAppFkMapper;
import com.justin.app_back.pojo.AppInfo;
import com.justin.app_back.pojo.AppVersion;
import com.justin.app_back.pojo.UsersApp;
import com.justin.app_back.service.AppInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Resource
    private AppInfoMapper appInfoMapper;

    @Resource
    private AppVersionMapper appVersionMapper;

    @Resource
    private UserAppFkMapper userAppFkMapper;

    @Override
    public PageInfo getPage(AppInfo appInfo, int pageNum) {

        PageHelper.startPage(pageNum, 5, "id asc");

        List<AppInfo> appInfoList = appInfoMapper.selectBy(appInfo);

        return new PageInfo(appInfoList);
    }

    @Override
    public PageInfo getCollectPage(AppInfo appInfo, int pageNum, Integer userId) {
        PageHelper.startPage(pageNum, 5, "id asc");

        List<AppInfo> appInfoList = appInfoMapper.selectCollectBy(appInfo,userId);

        return new PageInfo(appInfoList);
    }

    @Override
    public void cancelCollect(Integer appId, Integer userId) {
        userAppFkMapper.delete(appId, userId);
    }

    @Override
    public int update(AppInfo appInfo, Integer adminOrDevId) {

        if (appInfo.getId() == null) {


            if (appInfo.getSoftwarename().trim().isEmpty()) {
                throw new RuntimeException("请输入有用的游戏名");
            }


            appInfo.setCreateddate(new Date());
            appInfo.setDownloads(0L);
            appInfo.setStatus(1); // 待审核
            appInfo.setDevid(adminOrDevId);

            return appInfoMapper.insert(appInfo);



        } else {
            AppInfo appInfo1 = appInfoMapper.selectByPrimaryKey(appInfo.getId());
            if (!(Objects.equals(appInfo1.getDevid(), adminOrDevId))) {
                throw new RuntimeException("你只能修改你自己开发的游戏信息");
            }else {
                appInfo.setStatus(1); // 待审核
                appInfo.setModifydate(new Date());
                appInfo.setModifyby(adminOrDevId);
                return appInfoMapper.updateByPrimaryKeySelective(appInfo);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Integer id, String uploadPath) {

        int i =0;

        List<AppVersion> appVersions = appVersionMapper.selectByAppId(id);

        for (AppVersion appVersion : appVersions) {

            if (appVersion.getDownloadlink() != null) {
                File file = new File(uploadPath + appVersion.getDownloadlink());

                if (file.exists()) {
                    file.delete();
                }

                appVersionMapper.deleteByPrimaryKey(appVersion.getId());

            }

        }

        AppInfo appInfo = appInfoMapper.selectByPrimaryKey(id);
        if (appInfo.getLogopicpath() != null) {
            File file = new File(uploadPath + appInfo.getLogopicpath());
            if (file.exists()) {
                file.delete();
            }
            i = appInfoMapper.deleteByPrimaryKey(id);
        }
        return i;
    }

    @Override
    public boolean validateAPKName(String apkname, Integer id) {

        // 排除特殊情况 修改的时候用户没有修改apk名称

        if (id != null) {
            AppInfo appInfo1 = appInfoMapper.selectByPrimaryKey(id);

            if (appInfo1.getApkname().equals(apkname)) {
                return true;
            }
        }

        AppInfo appInfo = new AppInfo();
        appInfo.setApkname(apkname);

        List<AppInfo> appInfoList = appInfoMapper.selectBy(appInfo);

        if (appInfoList == null || appInfoList.size() == 0) {
            return true;
        }else
            return false;

    }

    @Override
    public AppInfo getById(Integer id) {
        return appInfoMapper.selectByPrimaryKey(id);

    }

    @Override
    public AppInfo getAppWithVersion(Integer appid) {
        AppInfo appInfo = appInfoMapper.selectByPrimaryKey(appid);
        appInfo.setVersions(appVersionMapper.selectByAppId(appid));
        return appInfo;
    }

    @Override
    public void reviewAppStatus(AppInfo appInfo, Integer adminId, Integer statusId) {

        AppInfo appInfo1 = appInfoMapper.selectByPrimaryKey(appInfo.getId());

        if (Objects.equals(appInfo1.getStatus(), statusId)) {
            appInfoMapper.updateByPrimaryKeySelective(appInfo1);
        } else {
            if (statusId == 4) {
                appInfo.setStatus(statusId);
                appInfo.setOnsaledate(new Date());
            } else if (statusId == 5) {
                appInfo.setStatus(statusId);
                appInfo.setOffsaledate(new Date());
            } else {
                appInfo.setStatus(statusId);
            }
            appInfo.setModifyby(adminId);
            appInfo.setModifydate(new Date());
            appInfoMapper.updateByPrimaryKeySelective(appInfo);
        }
    }

    @Override
    public void collectApp(UsersApp usersApp) {
        List<Integer> selectedAppId = appInfoMapper.selectByUserId(usersApp.getUserId());
        for (Integer i : selectedAppId) {
            if (i == usersApp.getAppId()) {
                throw new RuntimeException("已收藏该游戏");
            }
        }
            usersApp.setCreateddate(new Date());
            appInfoMapper.insertAppOfUser(usersApp);

    }


}
