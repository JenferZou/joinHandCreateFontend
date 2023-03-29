package com.hr.admin.controller;

import com.hr.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
//@RequestMapping(value = "/hrm-login")
public class LoginController {

    @Autowired
    private UserDetailsService userService ;

    @GetMapping(value = "/")
    public Object login(Map<String,String> maps) {
        System.out.println(userService.loadUserByUsername(maps.get("userName")));
        return userService.loadUserByUsername(maps.get("userName"));
    }

}
