package com.justin.app_back;

import com.justin.app_back.mapper.AppCategoryMapper;
import com.justin.app_back.pojo.AppCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AppBackApplicationTests {

    @Resource
    private AppCategoryMapper appCategoryMapper;

    @Test
    void contextLoads() {
        AppCategory appCategory = appCategoryMapper.selectByPrimaryKey(7);
        System.out.println(appCategory);
    }

}
