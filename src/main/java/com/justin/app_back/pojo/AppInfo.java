package com.justin.app_back.pojo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @TableName app_info
 */
@Data
public class AppInfo {
    private Integer id;

    private String softwarename;

    private String apkname;

    private String supportrom;

    private String interfacelanguage;

    private Double softwaresize;

    private Date updetedate;

    private Integer devid;

    private String appinfo;

    private Integer status;

    private Date onsaledate;

    private Date offsaledate;

    private Integer platformid;

    private String logopicpath;

    private Integer categorylevel1;

    private Integer categorylevel2;

    private String level1Name;

    private String level2Name;

    private Long downloads;

    private List<AppVersion> versions;

    private Integer createby;

    private Date createddate;

    private Integer modifyby;

    private Date modifydate;
}