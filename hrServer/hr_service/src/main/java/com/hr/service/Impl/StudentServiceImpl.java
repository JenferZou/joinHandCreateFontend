package com.hr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.mapper.StudentMapper;
import com.hr.model.PageResult;
import com.hr.model.Student;
import com.hr.service.StudentService;
import com.hr.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult queryStudent(Integer page,Integer limit) {
        return PageUtil.getPageResult(getPageInfo(page,limit));
    }

    @Override
    public int modifyStudent(Student student) {
        return studentMapper.updateStudentByPrimaryKey(student);
    }

    @Override
    public int deleteStudentBySno(Student student) {
        return studentMapper.deleteBySno(student);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public PageResult searchStudentByName(String studentName,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        List<Student> students=studentMapper.getAllStudentByName(studentName);
        PageInfo<Student> pageInfo=new PageInfo<>(students);
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchStudentByCondition(Student student,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        List<Student> students=studentMapper.getStudentByCondition(student);
        PageInfo<Student> pageInfo=new PageInfo<>(students);
        return PageUtil.getPageResult(pageInfo);
    }

    public Student getStudentBySno(String sno){
        return studentMapper.getStudentBySno(sno);
    }

    /**
     * 调用分页插件完成分页
     * @param
     * @return
     */
    private PageInfo<Student> getPageInfo(Integer page,Integer limit) {
        PageHelper.startPage(page, limit);
        List<Student> students = studentMapper.getAllStudent();
        return new PageInfo<Student>(students);
    }





}
