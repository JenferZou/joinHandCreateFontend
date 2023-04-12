package com.hr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.mapper.AdminMapper;
import com.hr.mapper.RoleMapper;
import com.hr.mapper.TeacherMapper;
import com.hr.model.*;
import com.hr.service.AdminService;
import com.hr.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageResult getAdminInformation(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Manager> managers=adminMapper.getAdminInformation();
        managers.forEach(manager -> {
            manager.setRoles(adminMapper.getUserRolesByUserId(manager.getNo()));
        });
        PageInfo<Manager> pageInfo=new PageInfo<>(managers);
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public int deleteAdmin(String id) {
        return adminMapper.deleteAdmin(id);
    }

    @Override
    public int saveUser(Map<String, String> map) {
        int i=0;
        String default_PASSWORD = "{bcrypt}$2a$10$PAiZ1ue3KxUs/lvitZZlOux1LaGXSDBZ2AGRTw1AzbT9nsYtYe5K.";
        if(Objects.equals(map.get("role"), "老师")){
            Teacher teacher=new Teacher();
            teacher.setNo(map.get("gno"));
            teacher.setName(map.get("name"));
            teacher.setPhone(map.get("gPhone"));
            teacher.setPassword(default_PASSWORD);
            i+= adminMapper.saveTeacher(teacher);
            MemberRole memberRole=new MemberRole();
            memberRole.setMid(map.get("gno"));
            memberRole.setRid("ROLE_TEACHER");
            i+= roleMapper.saveRole(memberRole);
        }else{
            Manager manager=new Manager();
            manager.setNo(map.get("gno"));
            manager.setName(map.get("name"));
            manager.setPhone(map.get("gPhone"));
            manager.setPassword(default_PASSWORD);
            manager.setStatus(1);
            i+= adminMapper.saveManger(manager);
            MemberRole memberRole=new MemberRole();
            memberRole.setMid(map.get("gno"));
            memberRole.setRid("ROLE_ADMIN");
            i+= roleMapper.saveRole(memberRole);
        }
        return i;
    }
}
