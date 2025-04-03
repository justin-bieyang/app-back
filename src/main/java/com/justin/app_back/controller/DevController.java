package com.justin.app_back.controller;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.DevUser;
import com.justin.app_back.service.DevService;
import com.justin.app_back.utils.JwtUtil;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Resources_sv;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 开发者控制层
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/dev")
public class DevController {

    @Resource
    private DevService devService;

    /**
     * 分页查询
     * @param devUser
     * @param pageNum
     * @return
     */
    @PostMapping("/page")
    public ResultVo getPage(@RequestBody DevUser devUser, @RequestParam(defaultValue = "1") int pageNum) {
        PageInfo pageInfo = devService.geyPage(devUser, pageNum);
        return ResultVo.success("开发人员分页查询成功",pageInfo);
    }


    /**
     * 更新开发者信息
     * @param devUser
     * @param request
     * @param userType
     * @return
     */
    @PostMapping("/update")
    public ResultVo update(@RequestBody DevUser devUser,
                           HttpServletRequest request,
                           @RequestParam String userType){

        String token = request.getHeader("token");
        String username = JwtUtil.getUsername(token);

        devService.updateDev(devUser,username,userType);

        return ResultVo.success("更新开发人员数据成功",null);
    }


    /**
     * 新增开发者接口（暂时没使用，可通过注册新增）
     * @param devUser
     * @param request
     * @return
     */
    @PostMapping("/add")
    public ResultVo add(@RequestBody DevUser devUser, HttpServletRequest request){
        String token = request.getHeader("token");
        String adminName = JwtUtil.getUsername(token);
        devService.addDev(devUser, adminName);
        return ResultVo.success("添加开发人员数据成功",null);
    }

    /**
     * 删除开发者接口
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultVo delete(@PathVariable Integer id){
        devService.deleteDev(id);
        return ResultVo.success("删除开发人员数据成功",null);
    }
}
