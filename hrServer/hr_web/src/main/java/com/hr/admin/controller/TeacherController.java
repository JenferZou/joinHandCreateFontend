package com.hr.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSONB;
import com.hr.model.*;
import com.hr.service.*;
import com.hr.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/teacher/")
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private DelieverService delieverService;


    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("deleteActive")
    public RespBean deleteProject(@RequestBody Project project) {
        int i = projectService.deleteOneById(project);
        if (i > 0)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败");
    }
    @GetMapping("project")
    public PageResult queryAllProject(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return projectService.getAllProject(page, limit);
    }
    @PostMapping("saveProject")
    public RespBean saveProject(@RequestBody Project project,HttpServletRequest request){
        String token = request.getHeader("UserToken");
        String tno = jwtUtil.getMemberIdByJwtToken(token);
        project.setTno(tno);
        int i=projectService.saveProject(project);
        if(i>0){
            return RespBean.ok("添加成功");
        }else
            return RespBean.error("保存失败");

    }
    @GetMapping("searchProject")
    public PageResult searchProject(@RequestParam("title") String title,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return projectService.searchProject(title, page, limit);
    }

    @GetMapping("studentDeliever")
    public PageResult getwaitStudentDeliever(HttpServletRequest request, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        String token = request.getHeader("UserToken");
        String tno = jwtUtil.getMemberIdByJwtToken(token);
        return delieverService.getwaitDelieverbytno(tno,page,limit);
    }



    @GetMapping("accessDeliever")
    public PageResult getaccessStudentDeliever(HttpServletRequest request, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        String token = request.getHeader("UserToken");
        String tno = jwtUtil.getMemberIdByJwtToken(token);
        return delieverService.getaccessDelieverbytno(tno,page,limit);
    }

    @PostMapping("refuseDeliever")
    public RespBean refuseDeliever(@RequestBody Deliever deliever){
        int i = delieverService.refuseDeliever(deliever);
        if (i > 0)
            return RespBean.ok("修改成功");
        else
            return RespBean.error("修改失败");

    }


    @PostMapping("agreeDeliever")
    public RespBean agreeDeliever(@RequestBody Deliever deliever){
        int i = delieverService.agreeDeliever(deliever);
        if (i > 0)
            return RespBean.ok("修改成功");
        else
            return RespBean.error("修改失败");


    }


    @PostMapping("lookStudentResume")
    public Map<String, Object> LookStudentResume(@RequestBody Deliever deliever){
        HashMap<String, Object> map = new HashMap<>();
        Student studentBySno = studentService.getStudentBySno(deliever.getSno());
        Resume resumeByid = resumeService.getResumeByid(studentBySno.getResumeId());
        map.put("studentInfo",studentBySno);
        map.put("resumeInfo",resumeByid);

        return map;

    }



    @GetMapping("searchDeliever")
    public PageResult searchDeliever(@RequestParam("name") String name,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return delieverService.getaDelieverbyname(name,page,limit);
    }


    @GetMapping("getMypublicPro")
    public PageResult getMypublicPro(HttpServletRequest request, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        String token = request.getHeader("UserToken");
        String tno = jwtUtil.getMemberIdByJwtToken(token);
        return projectService.getProjectbytno(tno,page,limit);
    }

    @GetMapping("searchAccessStudent")
    public List<Deliever> searchAccessStudent(Project project){
        System.out.println(delieverService.getaccessDelieverbytnoandpid(project.getTno(),project.getId()));
        return delieverService.getaccessDelieverbytnoandpid(project.getTno(),project.getId());
    }



}
