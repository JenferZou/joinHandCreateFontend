package com.hr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.mapper.AdminMapper;
import com.hr.model.Contest;
import com.hr.model.Manager;
import com.hr.model.PageResult;
import com.hr.service.AdminService;
import com.hr.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public PageResult getAdminInformation(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Manager> managers=adminMapper.getAdminInformation();
        PageInfo<Manager> pageInfo=new PageInfo<>(managers);
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public int deleteAdmin(String id) {
        return adminMapper.deleteAdmin(id);
    }
}
