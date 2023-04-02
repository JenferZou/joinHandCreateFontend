package com.hr.config;

import com.alibaba.fastjson.JSONObject;
import com.hr.model.RespBean;
import org.springframework.security.authentication.*;
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
        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 响应401的状态码
        if (exception instanceof LockedException) {

            response.getWriter().write(JSONObject.toJSONString(RespBean.error("账户被锁定，登录失败！")));
        } else if (exception instanceof BadCredentialsException) {

            response.getWriter().write(JSONObject.toJSONString(RespBean.error("账户名或密码输入错误，登录失败！")));
        } else if (exception instanceof DisabledException) {

            response.getWriter().write(JSONObject.toJSONString(RespBean.error("账户被锁定，登录失败！")));
        } else if (exception instanceof AccountExpiredException) {

            response.getWriter().write(JSONObject.toJSONString(RespBean.error("账户已过期，登录失败！")));
        } else if (exception instanceof CredentialsExpiredException) {

            response.getWriter().write(JSONObject.toJSONString(RespBean.error("密码已过期，登录失败！")));
        } else {

            response.getWriter().write(JSONObject.toJSONString(RespBean.error("未知原因，导致登录失败！")));
        }
    }
}
