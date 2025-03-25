package com.justin.app_back.pojo;

import java.util.Date;
import lombok.Data;

/**
 * @TableName date_dictionary
 */
@Data
public class DateDictionary {
    private Integer id;

    private String typecode;

    private String typename;

    private Integer valueid;

    private String valuename;

    private Integer createby;

    private Date createddate;

    private Integer modifyby;

    private Date modifydate;
}