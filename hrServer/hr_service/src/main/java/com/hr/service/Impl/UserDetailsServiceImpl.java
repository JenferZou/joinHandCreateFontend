package com.hr.service.Impl;

import com.hr.mapper.AdminMapper;
import com.hr.mapper.StudentMapper;
import com.hr.model.Manager;
import com.hr.model.Role;
import com.hr.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = adminMapper.getUserByUserName(username);
        Student student=studentMapper.getStudentBySno(username);
        if (ObjectUtils.isEmpty(manager)&&ObjectUtils.isEmpty(student)) {
            //抛异常
            throw  new UsernameNotFoundException("根据用户名找不到该用户的信息！");
        }
        if(!ObjectUtils.isEmpty(manager)) {
            //获取角色权限
            List<Role> list = adminMapper.getUserRolesByUserId(manager.getgNo());
            manager.setRoles(list);
            return manager;
        }
        List<Role> list =adminMapper.getUserRolesByUserId(student.getSno());
        student.setRoles(list);

        return student;
    }
}
