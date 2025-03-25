package com.justin.app_back.exception;

import com.justin.app_back.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo error(Exception e) {
        e.printStackTrace();
        return new ResultVo().error(e.getMessage());
    }

}
