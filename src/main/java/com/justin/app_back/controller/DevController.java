package com.justin.app_back.controller;

import com.github.pagehelper.PageInfo;
import com.justin.app_back.pojo.DevUser;
import com.justin.app_back.service.DevService;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

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
        return ResultVo.success("",pageInfo);
    }

    @PostMapping("/update")
    public ResultVo update(@RequestBody DevUser devUser){

        

        return null;
    }

}
