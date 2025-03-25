package com.justin.app_back.service.impl;

import com.justin.app_back.mapper.AppCategoryMapper;
import com.justin.app_back.pojo.AppCategory;
import com.justin.app_back.service.AppCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Service
public class AppCategoryServiceImpl implements AppCategoryService {

    @Resource
    private AppCategoryMapper appCategoryMapper;

    private AppCategory tree;

    @Override
    public AppCategory getTree() {
        // 查出所有分类
        List<AppCategory> appCategoryList = appCategoryMapper.selectAll();

        for (AppCategory appCategory : appCategoryList) {
            if (appCategory.getParentid() == null) {
                // 找孩子
                tree = findChildren(appCategory, appCategoryList);
            }
        }
        return tree;
    }

    private AppCategory findChildren(AppCategory appCategory, List<AppCategory> appCategoryList) {
        // 递归算法找孩子
        appCategory.setChildren(new ArrayList<>());

        for (AppCategory child : appCategoryList) {
            if (child.getParentid() == (appCategory.getId())){
                appCategory.getChildren().add(child);
                // 继续找孩子的孩子
                findChildren(child, appCategoryList);
            }
        }
        return appCategory;
    }
}
