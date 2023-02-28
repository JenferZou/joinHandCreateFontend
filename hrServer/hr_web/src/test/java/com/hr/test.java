package com.hr;

import com.hr.mapper.StudentMapper;
import com.hr.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class test {
    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void testGetStudent(){
        Student student=new Student();
        student.setsName("lihua");
        student.setBlood("ab");
        student.setClassName("软件一班");
        student.setMentor("凉");
        student.setRelatives("小张");
        student.setResumeId("2");
        student.setrPhone("121762376");
        student.setScore(10D);
        student.setsDepartment("计算机学院");
        student.setsMajor("软件工程");
        student.setSno("2136172721");
        student.setStar("双鱼");
        student.setsPhone("122342312");
         studentMapper.addStudent(student);
        System.out.println(studentMapper.getAllStudentByScore(100D,200D));

        //System.out.println(studentMapper.getAllStudentByClass("软件一班"));
        //System.out.println(studentMapper.getAllStudentByName("lihua"));
        //System.out.println(studentMapper.updateByPrimaryKey(student));
        //System.out.println(studentMapper.addStudent(student));
        //System.out.println(studentMapper.deleteByName("lihua"));
        //System.out.println(studentMapper.getAllStudent());
    }
}
