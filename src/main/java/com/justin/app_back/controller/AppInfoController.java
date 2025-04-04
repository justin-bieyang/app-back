package com.justin.app_back.controller;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.AppInfo;
import com.justin.app_back.pojo.UsersApp;
import com.justin.app_back.service.AppInfoService;
import com.justin.app_back.utils.JwtUtil;
import com.justin.app_back.vo.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * app信息控制类
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/appInfo")
public class AppInfoController {

    @Resource
    private AppInfoService appInfoService;

    @Value("${upload.path}")
    private String uploadPath;

    /**
     * 查询app基本信息的同时，把该app的版本返回
     * @param appid
     * @return
     */
    @GetMapping("/appWithVersion/{appid}")
    public ResultVo appWithVersion(@PathVariable("appid") Integer appid) {
        AppInfo appInfo = appInfoService.getAppWithVersion(appid);
        return ResultVo.success("", appInfo);
    }

    // 上传logo图片

    @PostMapping("/logo/{id}")
    public ResultVo upLogo(@PathVariable Integer id, MultipartFile logo, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);
        if (logo != null && !logo.isEmpty()) {
            // 说明前端上传了图片
            String originalFilename = logo.getOriginalFilename(); // 获取文件名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取文件格式

            if (logo.getSize() > 1024 * 1024) { // 不超过1M
                return ResultVo.error("文件过大，上传不要大于1M!");
            } else if (
                    suffix.equalsIgnoreCase(".jpg") ||
                            suffix.equalsIgnoreCase(".png") ||
                            suffix.equalsIgnoreCase(".gif") ||
                            suffix.equalsIgnoreCase(".jpeg")
            ) {

                // 上传文件
                File savePath = new File(uploadPath);

                if (!savePath.exists()) {
                    savePath.mkdirs();
                }

                // 文件起新名字

                String newFileName = UUID.randomUUID().toString().replace("-", "");
                File saveFile = new File(uploadPath + newFileName + suffix);
                try {
                    logo.transferTo(saveFile);

                    // 文件上传成功

                    // 保存到数据库
                    AppInfo appInfo = appInfoService.getById(id);

                    appInfo.setLogopicpath(newFileName + suffix);

                    appInfoService.update(appInfo,username);

                    return ResultVo.success("上传logo成功！", null);

                } catch (IOException e) {
                    throw new RuntimeException("文件上传异常！");
                }

            } else {
                return ResultVo.error("文件格式不对，必须是图片！");
            }
        } else {
            return ResultVo.error("必须上传文件！");
        }
    }

    /**
     * 分页查询
     * @param appInfo
     * @param pageNum
     * @return
     */
    @PostMapping("/page")
    public ResultVo page(@RequestBody AppInfo appInfo, @RequestParam(defaultValue = "1") int pageNum) {

        PageInfo pageInfo = appInfoService.getPage(appInfo, pageNum);

        return ResultVo.success("", pageInfo);

    }

    /**
     * 校验apkName不能重复
     * @param apkname
     * @param id
     * @return
     */
    @GetMapping("/validate")
    public ResultVo validateAPKName(@RequestParam(required = true) String apkname, @RequestParam(required = false) Integer id) {

        boolean fag = appInfoService.validateAPKName(apkname, id);

        return ResultVo.success("", fag);

    }


    /**
     * 新增和修改接口
     * @param appInfo
     * @param request
     * @return
     */
    @PostMapping("/update")
    public ResultVo update(@RequestBody AppInfo appInfo, HttpServletRequest request) {

        String token = request.getHeader("token");

        String username = JwtUtil.getUsername(token);

        int update = appInfoService.update(appInfo, username);

        if (update != 0) {
            return ResultVo.success("数据操作成功！", null);
        } else
            return ResultVo.error("数据操作失败！");

    }


    /**
     * 删除app
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultVo deleteById(@PathVariable Integer id) {

        int delete = appInfoService.deleteById(id, uploadPath);

        if (delete != 0) {
            return ResultVo.success("数据删除成功！", null);
        } else
            return ResultVo.error("数据更删除失败！");

    }


    /**
     * 审核app状态
     * @param appInfo
     * @param request
     * @param statusId
     * @return
     */
    @PostMapping("/review")
    public ResultVo reviewStatus(@RequestBody AppInfo appInfo,
                                 HttpServletRequest request,
                                 @RequestParam Integer statusId) {

        String token = request.getHeader("token");
        String adminUsername = JwtUtil.getUsername(token);

        appInfoService.reviewAppStatus(appInfo, adminUsername, statusId);

        return ResultVo.success("审核完成", null);

    }


    /**
     * 收藏app接口
     * @param usersApp
     * @param request
     * @return
     */
    @PostMapping("/collect")
    public ResultVo collect(@RequestBody UsersApp usersApp, HttpServletRequest request) {

        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);

        appInfoService.collectApp(usersApp, username);

        return ResultVo.success("收藏成功",null);
    }

    /**
     * 收藏页的分页查询
     * @param appInfo
     * @param pageNum
     * @param request
     * @return
     */
    @PostMapping("/collectPage")
    public ResultVo collectPage(@RequestBody AppInfo appInfo,
                                @RequestParam(defaultValue = "1") int pageNum,
                                HttpServletRequest request) {

        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);

        PageInfo pageInfo = appInfoService.getCollectPage(appInfo, pageNum, username);

        return ResultVo.success("", pageInfo);
    }


    /**
     * 取消收藏接口
     * @param appId
     * @param request
     * @return
     */
    @DeleteMapping("/cancelCollect/{appId}")
    public ResultVo cancelCollect(@PathVariable Integer appId,
                                  HttpServletRequest request){

        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);

        appInfoService.cancelCollect(appId, username);

        return ResultVo.success("取消收藏成功",null);
    }
}