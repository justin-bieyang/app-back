package com.justin.app_back.controller;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.DevUser;
import com.justin.app_back.service.DevService;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Resources_sv;

import javax.annotation.Resource;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/dev")
public class DevController {

    @Resource
    private DevService devService;

    @PostMapping("/page")
    public ResultVo getPage(@RequestBody DevUser devUser, @RequestParam(defaultValue = "1") int pageNum) {
        PageInfo pageInfo = devService.geyPage(devUser, pageNum);
        return ResultVo.success("开发人员分页查询成功",pageInfo);
    }

    @PostMapping("/update")
        public ResultVo update(@RequestBody DevUser devUser,
                               @RequestParam("adminOrDevId") Integer adminOrDevId,
                               @RequestParam String userType){

        devService.updateDev(devUser,adminOrDevId,userType);

        return ResultVo.success("更新开发人员数据成功",null);
    }

    @PostMapping("/add")
    public ResultVo add(@RequestBody DevUser devUser, Integer adminId){
        devService.addDev(devUser, adminId);
        return ResultVo.success("添加开发人员数据成功",null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultVo delete(@PathVariable Integer id){
        devService.deleteDev(id);
        return ResultVo.success("添加开发人员数据成功",null);
    }
}
