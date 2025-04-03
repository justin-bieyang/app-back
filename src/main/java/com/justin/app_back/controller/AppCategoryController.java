package com.justin.app_back.controller;

import com.justin.app_back.pojo.AppCategory;
import com.justin.app_back.service.AppCategoryService;
import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * app种类控制层
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/appCategory")
public class AppCategoryController {

    @Resource
    private AppCategoryService appCategoryService;

    /**
     *拿到游戏分类节点数
     * @return
     */
    @GetMapping("/tree")
    public ResultVo getTree() {

        AppCategory appCategoryTree = appCategoryService.getTree();
        return ResultVo.success("查询成功", appCategoryTree);

    }

}
