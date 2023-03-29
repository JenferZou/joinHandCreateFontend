package com.hr.mapper;

import com.hr.model.Manager;
import com.hr.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    public List<Manager> getAdminInformation();
    public int deleteAdmin(String id);
    public Manager getUserByUserName(String userName);
   public List<Role> getUserRolesByUserId(String no);
}
