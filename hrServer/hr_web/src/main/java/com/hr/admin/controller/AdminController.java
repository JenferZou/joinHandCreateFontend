package com.hr.admin.controller;

import com.hr.model.PageResult;
import com.hr.model.RespBean;
import com.hr.model.Student;
import com.hr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private StudentService studentService;
    @GetMapping("listStudent")
    public PageResult listStudent(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return studentService.queryStudent(page, limit);
    }
    @PostMapping("modify")
    public RespBean modifyStudent(@RequestBody Student student){
        int i=studentService.modifyStudent(student);
        if(i>0)
        return RespBean.ok("修改成功");
        else
            return RespBean.error("修改失败");
    }
}
