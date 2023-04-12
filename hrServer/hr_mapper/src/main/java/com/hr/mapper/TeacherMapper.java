package com.hr.mapper;

import com.hr.model.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
   Teacher getTeacherBySno(String username);
   public List<Teacher> getAll();
}
