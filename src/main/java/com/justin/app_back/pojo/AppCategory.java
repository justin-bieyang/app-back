package com.justin.app_back.pojo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @TableName app_category
 */
@Data
public class AppCategory {
    private Integer id;

    private String categorycode;

    private String categoryname;

    private Integer parentid;

    private Integer createdby;

    private Date createdtime;

    private Integer modifyby;

    private Date modifydate;

    private List<AppCategory> children;

}