package com.justin.app_back.pojo;

import java.util.Date;
import lombok.Data;

/**
 * @TableName backend_user
 */
@Data
public class BackendUser {
    private Integer id;

    private String usercode;

    private String username;

    private Integer usertype;

    private Date createdate;

    private Integer modifyby;

    private Date modifydate;

    private String userpassword;
}