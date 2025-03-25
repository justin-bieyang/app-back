package com.justin.app_back.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName app_version
 */
@Data
public class AppVersion {
    private Integer id;

    private Integer appid;

    private String versioninfo;

    private Integer publishstatus;

    private String downloadlink;

    private Double versionsize;

    private Integer createdby;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createddate;

    private Integer modifyby;

    private Date modifydate;
}