package com.hr.service;

import com.hr.model.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface AdminService {
    public PageResult getAdminInformation( Integer page,Integer limit);
   public int deleteAdmin(String id);
   public int saveUser(Map<String,String> map);
}
