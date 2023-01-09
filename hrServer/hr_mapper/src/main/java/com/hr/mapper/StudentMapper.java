package com.hr.mapper;

import com.hr.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    public List<Student> getAllStudent();
    public int addStudent(Student student);
    public int deleteByName(String name);
    public int updateByPrimaryKey(Student student);
    public List<Student> getAllStudentByName(String name);
    public List<Student> getAllStudentByClass(String clazz);
    public List<Student> getAllStudentByScore(@Param("minScore") Double minScore, @Param("maxScore") Double maxScore);
}
