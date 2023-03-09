package com.hr.admin.controller;

import com.hr.model.Contest;
import com.hr.model.PageResult;
import com.hr.model.RespBean;
import com.hr.model.Student;
import com.hr.service.ContestService;
import com.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController()
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private StudentService studentService;
@Autowired
private ContestService contestService;
    @GetMapping("listStudent")
    public PageResult listStudent(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return studentService.queryStudent(page, limit);
    }

    @PostMapping("modify")
    public RespBean modifyStudent(@RequestBody Student student) {
        int i = studentService.modifyStudent(student);
        if (i > 0)
            return RespBean.ok("修改成功");
        else
            return RespBean.error("修改失败");
    }

    @PostMapping ("delete")
    public RespBean deleteStudent(@RequestBody Student student) {
        System.out.println(student);
        int i = studentService.deleteStudentBySno(student);
        if (i > 0)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败");
    }

    @PostMapping("add")
    public RespBean addStudent(@RequestBody Student student) {
        int i = studentService.addStudent(student);
        if (i > 0)
            return RespBean.ok("添加成功");
        else
            return RespBean.error("删除失败");
    }

    @GetMapping("search")
    public PageResult searchStudent(@RequestParam("studentName") String studentName,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return studentService.searchStudentByName(studentName, page, limit);
    }

    @GetMapping("highSearch")
    public PageResult highSearchStudent(@RequestParam("sno") String sno,@RequestParam("sName") String sName,
    @RequestParam("gender") String gender,@RequestParam("className") String className,
    @RequestParam("sMajor") String sMajor,@RequestParam("sDepartment") String sDepartment,
    @RequestParam("mentor") String mentor,@RequestParam("relatives") String relatives,
    @RequestParam("currentPage") Integer page, @RequestParam("pageSize") Integer limit
    ) {
        Student student=new Student();
        student.setSno(sno);
        student.setsName(sName);
        student.setGender(gender);
        student.setClassName(className);
        student.setsMajor(sMajor);
        student.setsDepartment(sDepartment);
        student.setMentor(mentor);
        student.setRelatives(relatives);
        return studentService.searchStudentByCondition(student,page,limit);
    }
    @GetMapping("contest")
    public PageResult getAllContestInformation(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return contestService.getAllContestInformation(page,limit);
    }
    @PostMapping("publish")
    public RespBean publishContest(@RequestBody Contest contest) throws ParseException {
        contest.setId(UUID.randomUUID().toString().substring(0,10));
        int i=0;
        i+=contestService.addContest(contest);
        if(i>0)
            return RespBean.ok("发布成功");
        else
        return RespBean.error("发布失败");
    }
    @PostMapping("deleteContest")
    public RespBean deleteStudent(@RequestBody Contest contest) {
       int i=contestService.deleteContest(contest);
        if (i > 0)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败");
    }
    @GetMapping("searchContest")
    public PageResult searchContest(@RequestParam("title") String title,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return contestService.searchContest(title, page, limit);
    }
}
