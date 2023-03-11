package com.hr.mapper;

import com.hr.model.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    public List<Manager> getAdminInformation();
    public int deleteAdmin(String id);
}
