package com.hr.mapper;

import com.hr.model.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
   Teacher getTeacherBySno(String username);
}
