package com.hr.config;

import com.alibaba.fastjson.JSONObject;
import com.hr.model.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("认证失败");
        // 指定响应格式是json
        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().write(JSONObject.toJSONString(RespBean.error("认证失败")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
