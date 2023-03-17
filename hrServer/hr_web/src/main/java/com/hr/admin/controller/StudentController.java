package com.hr.admin.controller;

import com.hr.model.*;
import com.hr.service.ActiveService;
import com.hr.service.CompanyService;
import com.hr.service.ContestService;
import com.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ActiveService activeService;

    @GetMapping("/StudentMessageForm")
    public Student getStudentBySno(@RequestParam("sno") String sno){
        return studentService.getStudentBySno(sno);
    }


    @GetMapping("/student/contest")
    public List<Contest> getThreeContest(){
        return  contestService.getThreeContest();
    }


    @GetMapping("/student/active")
    public List<Active> getThreeActive(){
        return  activeService.getThreeActive();
    }



    @GetMapping("/student/company")
    public List<Company> getFourCompany(){
        return  companyService.QueryFourCompany();
    }


}
