package com.hr.admin.controller;

import com.hr.model.*;
import com.hr.service.ActiveService;
import com.hr.service.ContestService;
import com.hr.service.ResumeService;
import com.hr.service.StudentService;
import com.hr.utils.JWTConfigProperties;
import com.hr.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: StudentController
 * Description:
 * date: 2023/3/11 15:54
 *
 * @author: Jenf
 * @version:
 * @since JDK 17
 */
@RestController()
@RequestMapping("/student/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private ActiveService activeService;

    @Autowired
    private ResumeService resumeService;


    @Autowired
    private JWTConfigProperties jwtConfigProperties;


    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("StudentMessageForm")
    public Student getStudentBySno(HttpServletRequest request){
        System.out.println(jwtConfigProperties);
        String token = request.getHeader("UserToken");
        String sno = jwtUtil.getMemberIdByJwtToken(token);
        System.out.println(studentService.getStudentBySno(sno));
        return studentService.getStudentBySno(sno);
    }


    @GetMapping("contest")
    public List<Contest> getThreeContest(){
        return  contestService.getThreeContest();
    }


    @GetMapping("active")
    public List<Active> getThreeActive(){
        return  activeService.getThreeActive();
    }

    @PostMapping("StudentResume")
    public Resume getResumeByid( HttpServletRequest request){
        String token = request.getHeader("UserToken");
        String sno = jwtUtil.getMemberIdByJwtToken(token);
        Student studentBySno = studentService.getStudentBySno(sno);
        Resume resumeByid = resumeService.getResumeByid(studentBySno.getResumeId());
        return resumeByid;
    }







}
