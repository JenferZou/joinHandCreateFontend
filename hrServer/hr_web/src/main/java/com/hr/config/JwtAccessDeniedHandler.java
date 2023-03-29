package com.hr.config;

import com.alibaba.fastjson.JSONObject;
import com.hr.model.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义无权访问处理类
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e)  {
        System.out.println(httpServletRequest.getHeader("UserToken"));
       /* Result r = new Result();
        r.code(ResultCode.FORBIDDEN).message("权限不足");
        String json =  JSON.toJSONString(r);*/
        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().write(JSONObject.toJSONString(RespBean.error("权限不足")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

