package com.justin.app_back.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {
    private Integer code; // 200 500
    private String msg;
    private Object data; // 携带的数据

    public static ResultVo success(String msg, Object data) {
        return new ResultVo(200, msg, data);
    }

    public static ResultVo error(String msg) {
        return new ResultVo(500, msg, null);
    }

    public static ResultVo reject(String msg) {
        return new ResultVo(300, msg, null);
    }


}
