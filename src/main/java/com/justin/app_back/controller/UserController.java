package com.justin.app_back.controller;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.BackendUser;
import com.justin.app_back.service.BackendUserService;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private BackendUserService backendUserService;
    @PostMapping("/page")
    public ResultVo page(@RequestBody BackendUser backendUser,@RequestParam(defaultValue = "1") int pageNum) {
        PageInfo pageInfo = backendUserService.getPage(backendUser,pageNum);
        return ResultVo.success("",pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    public ResultVo delete(@PathVariable("id") Integer id) {
        backendUserService.deleteById(id);
        return ResultVo.success("",null);
    }

    @PostMapping("/updateType")
    public ResultVo updateType(@RequestBody BackendUser backendUser) {
        backendUserService.updateUserType(backendUser);
        return ResultVo.success("",null);
    }




}
