package com.hr.config;

import com.alibaba.fastjson.JSONObject;
import com.hr.model.RespBean;
import com.hr.model.Role;
import com.hr.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 自定义认证成功处理器
 */

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication){
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        //将生成的authentication放入容器中，生成安全的上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //System.out.println(userDetails);
        //生成token
        final String realToken = jwtTokenUtil.createToken(userDetails.getUsername());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", realToken);
        map.put("Role",userDetails.getAuthorities());

        httpServletResponse.setContentType("text/json;charset=utf-8");
        try {
            httpServletResponse.getWriter().write(JSONObject.toJSONString(RespBean.ok("登录成功",map)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}