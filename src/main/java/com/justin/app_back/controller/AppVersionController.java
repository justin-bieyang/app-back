package com.justin.app_back.controller;

import com.justin.app_back.mapper.AppVersionMapper;
import com.justin.app_back.pojo.AppInfo;
import com.justin.app_back.pojo.AppVersion;
import com.justin.app_back.service.AppVersionServion;
import com.justin.app_back.vo.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/version")
public class AppVersionController {


    @Value("${upload.path}")
    private String uploadPath;

    @Resource
    private AppVersionServion appVersionServion;


    @PostMapping
    public ResultVo addVersion(AppVersion appVersion, MultipartFile apkFile) {

        if (apkFile != null && !apkFile.isEmpty()) {

            // 说明前端上传了文件
            String originalFilename = apkFile.getOriginalFilename(); // 获取文件名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取文件格式

            if(apkFile.getSize() > 1024*1024*10){ // 不超过10M
                return ResultVo.error("文件过大，上传不要大于10M!");
            } else if(
                    suffix.equalsIgnoreCase(".apk")
            ) {

                // 上传文件
                File savePath = new File(uploadPath);

                if(!savePath.exists()){
                    savePath.mkdirs();
                }

                // 文件起新名字

                String newFileName = UUID.randomUUID().toString().replace("-", "");
                File saveFile = new File(uploadPath+newFileName+suffix);
                try {
                    apkFile.transferTo(saveFile);

                    // 文件上传成功
                    appVersion.setDownloadlink(newFileName+suffix);
                    appVersion.setCreatedby(1);
                    appVersion.setCreateddate(new Date());
                    appVersion.setPublishstatus(3);


                    // 保存到数据库
                    appVersionServion.addApk(appVersion);

                    return ResultVo.success("数据提交成功！",null);

                } catch (IOException e) {
                    throw new RuntimeException("数据提交异常！");
                }

            }else {
                return ResultVo.error("文件格式不对，必须是apk文件！");
            }
        }else {
            return ResultVo.error("必须上传apk文件！");
        }


    }

}
