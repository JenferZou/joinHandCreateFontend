package com.hr.service;

import com.hr.model.PageResult;
import com.hr.model.Student;

import java.util.List;

public interface StudentService {
    public PageResult queryStudent(Integer page,Integer limit);
    public int modifyStudent(Student student);
    public int deleteStudentBySno(Student student);
    public int addStudent(Student student);
    public PageResult searchStudentByName(String studentName,Integer page,Integer limit);
    public PageResult searchStudentByCondition(Student student,Integer page,Integer limit);
}
