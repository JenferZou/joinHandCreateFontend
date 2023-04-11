package com.hr.admin.controller;

import com.hr.model.*;
import com.hr.service.*;
import com.hr.utils.JWTConfigProperties;
import com.hr.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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
    private ProjectService projectService;

    @Autowired
    private JWTUtil jwtUtil;


    @Autowired
    private DelieverService delieverService;

    @GetMapping("StudentMessageForm")
    public Student getStudentBySno(HttpServletRequest request){

        String token = request.getHeader("UserToken");
        String sno = jwtUtil.getMemberIdByJwtToken(token);

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

    @GetMapping("StudentResume")
    public Resume getResumeByid( HttpServletRequest request){
        String token = request.getHeader("UserToken");
        String sno = jwtUtil.getMemberIdByJwtToken(token);
        Student studentBySno = studentService.getStudentBySno(sno);
        if(studentBySno.getResumeId()==null){
            studentBySno.setResumeId(UUID.randomUUID().toString().substring(0,10));
            resumeService.insertResume(studentBySno.getResumeId(),sno);
        }
        Resume resumeByid = resumeService.getResumeByid(studentBySno.getResumeId());
        return resumeByid;
    }

    @GetMapping("project")
    public List<Project> getThreeProject(){
        return projectService.getThreeProject();
    }


    @PostMapping("edit")
    public RespBean editResume(@RequestBody Resume resume){
        int i = resumeService.updateAll(resume);
        if (i > 0)
            return RespBean.ok("修改成功");
        else
            return RespBean.error("修改失败");
    }


    @PostMapping("editMessage")
    public RespBean  editMess(@RequestBody Student student){
        int i = studentService.modifyStudent(student);
        if (i > 0)
            return RespBean.ok("修改成功");
        else
            return RespBean.error("修改失败");
    }




    @GetMapping("searchContest")
    public PageResult searchContest(@RequestParam("title") String title,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return contestService.searchContest(title, page, limit);
    }


    @GetMapping("contests")
    public PageResult getAllContestInformation(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return contestService.getAllContestInformation(page, limit);
    }


    @GetMapping("getActive")
    public PageResult queryAllActive(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return activeService.getAllActive(page, limit);
    }


    @GetMapping("searchProject")
    public PageResult searchProject(@RequestParam("title") String title,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return projectService.searchProject(title, page, limit);
    }


    @GetMapping("projects")
    public PageResult queryAllProject(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return projectService.getAllProject(page, limit);
    }



    @PostMapping("adddeliever")
    public RespBean addDeliever(HttpServletRequest request,@RequestBody Project project){
        String token = request.getHeader("UserToken");
        String sno = jwtUtil.getMemberIdByJwtToken(token);
        Student studentBySno = studentService.getStudentBySno(sno);
        if(delieverService.querySamewaitDeliever(sno,project.getId())==null){
            Deliever deliever = new Deliever();
            deliever.setId(UUID.randomUUID().toString().substring(0,10));
            deliever.setContent(project.getContent());
            deliever.setsMajor(studentBySno.getsMajor());
            deliever.setPid(project.getId());
            deliever.setsName(studentBySno.getsName());
            deliever.setSno(sno);
            deliever.setTno(project.getTno());
            deliever.setProjectName(project.getName());
            deliever.setMark(0);
            deliever.setMentor(project.getMentor());
            delieverService.addDelievet(deliever);
        }
        else{
            return RespBean.error("不可重复投递简历，请您耐心等待结果！");
        }

        return RespBean.ok("简历投递成功,请您耐心等待结果！");

    }


    @GetMapping("searchDeliever")
    public PageResult searchDeliever(@RequestParam("title") String title,
                                    @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        return delieverService.searchDelieverByPname(title,page,limit);
    }


    @GetMapping("Deliever")
    public PageResult queryAllDeliever(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return delieverService.getAllDeliever(page,limit);
    }



}
