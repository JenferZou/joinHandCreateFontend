package com.hr.config;

import com.alibaba.fastjson.JSONObject;
import com.hr.model.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理类
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        System.out.println("无凭证");
        // 指定响应格式是json
        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().write(JSONObject.toJSONString(RespBean.error("无凭证")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
