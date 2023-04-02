package com.hr.admin.controller;

import com.hr.model.*;
import com.hr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ActiveService activeService;
    @Autowired
    private ProjectService projectService;

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

    @PostMapping("delete")
    public RespBean deleteStudent(@RequestBody Student student) {
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
    public PageResult highSearchStudent(@RequestParam("sno") String sno, @RequestParam("sName") String sName,
                                        @RequestParam("gender") String gender, @RequestParam("className") String className,
                                        @RequestParam("sMajor") String sMajor, @RequestParam("sDepartment") String sDepartment,
                                        @RequestParam("mentor") String mentor, @RequestParam("relatives") String relatives,
                                        @RequestParam("currentPage") Integer page, @RequestParam("pageSize") Integer limit
    ) {
        Student student = new Student();
        student.setSno(sno);
        student.setsName(sName);
        student.setGender(gender);
        student.setClassName(className);
        student.setsMajor(sMajor);
        student.setsDepartment(sDepartment);
        student.setMentor(mentor);
        student.setRelatives(relatives);
        return studentService.searchStudentByCondition(student, page, limit);
    }

    @GetMapping("contest")
    public PageResult getAllContestInformation(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return contestService.getAllContestInformation(page, limit);
    }

    @PostMapping("publish")
    public RespBean publishContest(@RequestBody Contest contest) throws ParseException {
        contest.setId(UUID.randomUUID().toString().substring(0, 10));
        int i = 0;
        i += contestService.addContest(contest);
        if (i > 0)
            return RespBean.ok("发布成功");
        else
            return RespBean.error("发布失败");
    }

    @PostMapping("deleteContest")
    public RespBean deleteStudent(@RequestBody Contest contest) {
        int i = contestService.deleteContest(contest);
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

    @PostMapping("deleteContestBatch")
    public RespBean deleteContestBatch(@RequestBody List<String> contestsIds) {
        int i = contestService.removeMultiple(contestsIds);
        if (i > 0)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败");
    }

    @GetMapping("getAllUser")
    public PageResult getALLAdminUser(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return adminService.getAdminInformation(page, limit);
    }

    @PostMapping("deleteUser")
    public RespBean deleteAdmin(@RequestBody Map<String, String> maps) {
        int i = adminService.deleteAdmin(maps.get("id"));
        if (i > 0)
            return RespBean.ok("删除成功");
        return RespBean.error("删除失败");
    }

    @GetMapping("getActive")
    public PageResult queryAllActive(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return activeService.getAllActive(page, limit);
    }

    @PostMapping("saveActive")
    public RespBean saveActive(@RequestBody Active active) {
        int i = activeService.saveActive(active);
        if (i > 0)
            return RespBean.ok("添加成功");
        else
            return RespBean.error("添加失败");
    }

    @PostMapping("deleteActive")
    public RespBean deleteActive(@RequestBody Active active) {
        int i = activeService.deleteOneById(active);
        if (i > 0)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败");
    }

    @GetMapping("project")
    public PageResult queryAllProject(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return projectService.getAllProject(page, limit);
    }
    @PostMapping("deleteProject")
    public RespBean deleteProject(@RequestBody Project project) {
        int i = projectService.deleteOneById(project);
        if (i > 0)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败");
    }
    @GetMapping("searchProject")
    public PageResult searchProject(@RequestParam("title") String title,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return projectService.searchProject(title, page, limit);
    }

}
