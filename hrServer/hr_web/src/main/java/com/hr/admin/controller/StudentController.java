package com.hr.admin.controller;

import com.hr.model.RespBean;
import com.hr.model.Student;
import com.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/StudentMessageForm")
    public Student getStudentBySno(@RequestParam("sno") String sno){
        return studentService.getStudentBySno(sno);
    }


}
