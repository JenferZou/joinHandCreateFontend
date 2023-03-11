package com.hr.service;

import com.hr.model.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdminService {
    public PageResult getAdminInformation( Integer page,Integer limit);
   public int deleteAdmin(String id);
}
