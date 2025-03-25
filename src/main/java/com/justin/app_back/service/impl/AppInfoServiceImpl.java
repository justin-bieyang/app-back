package com.justin.app_back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.justin.app_back.mapper.AppInfoMapper;
import com.justin.app_back.mapper.AppVersionMapper;
import com.justin.app_back.pojo.AppInfo;
import com.justin.app_back.pojo.AppVersion;
import com.justin.app_back.service.AppInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
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

    @Override
    public PageInfo getPage(AppInfo appInfo, int pageNum) {

        PageHelper.startPage(pageNum, 5, "id asc");

        List<AppInfo> appInfoList = appInfoMapper.selectBy(appInfo);

        return new PageInfo(appInfoList);
    }

    @Override
    public int update(AppInfo appInfo) {

        if (appInfo.getId() == null) {

            appInfo.setDevid(new Random().nextInt(2) + 1);
            appInfo.setCreateddate(new Date());
            appInfo.setDownloads(0L);
            appInfo.setStatus(1); // 待审核

            return appInfoMapper.insert(appInfo);



        } else {
            appInfo.setModifydate(new Date());
            return appInfoMapper.updateByPrimaryKeySelective(appInfo);
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
}
