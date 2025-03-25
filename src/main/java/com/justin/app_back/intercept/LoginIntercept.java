package com.justin.app_back.intercept;

import com.alibaba.druid.support.json.JSONUtils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import com.justin.app_back.utils.JwtUtil;
import com.justin.app_back.vo.ResultVo;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public class LoginIntercept implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 检测前端发过来的token

        response.setContentType("application/json;charset=utf-8");
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {

            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultVo.reject("token令牌格式不对")));
            writer.close();
            writer.flush();
            return false;

        }

        try {
            JwtUtil.getUsername(token);

            return true;

        } catch (Exception e) {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultVo.reject(e.getMessage())));
            writer.close();
            writer.flush();
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) throws Exception {
    }

}
