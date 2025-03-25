package com.justin.app_back.pojo;

import java.util.Date;
import lombok.Data;

/**
 * 开发者
 * @TableName dev_user
 */
@Data
public class DevUser {
    private Integer id;

    private String devcode;

    private String devname;

    private String devpassword;

    private String devemail;

    private String devinfo;

    private Integer createdby;

    private Date createddate;

    private Integer modifyby;

    private Date modifydate;
}