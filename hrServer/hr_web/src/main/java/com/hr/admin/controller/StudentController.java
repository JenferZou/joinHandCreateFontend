package com.hr.admin.controller;

import com.hr.model.*;
import com.hr.service.ActiveService;
import com.hr.service.ContestService;
import com.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("StudentMessageForm")
    public Student getStudentBySno(@RequestParam("sno") String sno){
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


}
