package com.justin.app_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevRegisterDto {
    private String devName;
    private String devPassword;
    private String devEmail;
    private String devInfo;
}
