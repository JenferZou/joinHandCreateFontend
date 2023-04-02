package com.hr.admin.controller;

import com.hr.model.RespBean;
import com.hr.model.Student;
import com.hr.service.StudentService;
import com.hr.utils.ExcelHandle;
import com.hr.utils.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

@RestController
public class ExcelController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentService studentService;
    @GetMapping(value = "/excel/exportBankCheckInfo")
    public void ExportBankCkeckInfo(HttpServletResponse response, HttpServletRequest request){
        //这里是笔者实际业务需求中需要得到时间间隔。可忽略
        //得到所有要导出的数据
        List<Student> students =studentService.exportStudentExcel();
        //定义导出的excel名字
        String excelName = "学生表";
        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("id","编号");
        fieldMap.put("sName","姓名");
        fieldMap.put("sno","学号");
        fieldMap.put("gender","性别");
        fieldMap.put("sMajor","专业");
        fieldMap.put("className","班级");
        fieldMap.put("sDepartment","所属学院");
        fieldMap.put("sPhone","电话");
        fieldMap.put("star","星座");
        fieldMap.put("blood","血型");
        fieldMap.put("mentor","指导老师");
        fieldMap.put("mentorPhone","指导老师电话");
        fieldMap.put("relatives","联系人");
        fieldMap.put("rPhone","联系人电话");
        fieldMap.put("isGraduate","状态");
        fieldMap.put("score","积分");
        //fieldMap.put("简历","resumeId");
        //导出用户相关信息
        new ExportExcelUtils();
        ExportExcelUtils.export(excelName,students,fieldMap,response);
    }
    @PostMapping("/excel/leadExcel")
    public RespBean leadExcel(@RequestBody MultipartFile file)  {
        int i=0;
        if(file==null){
            return RespBean.error("文件为空");
        }
        try {
            InputStream inputStream=file.getInputStream();
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();

            fieldMap.put("姓名","sName");
            fieldMap.put("学号","sno");
            fieldMap.put("性别","gender");
            fieldMap.put("专业","sMajor");
            fieldMap.put("班级","className");
            fieldMap.put("所属学院","sDepartment");
            fieldMap.put("电话","sPhone");
            fieldMap.put("星座","star");
            fieldMap.put("血型","blood");
            fieldMap.put("指导老师","mentor");
            fieldMap.put("指导老师电话","mentorPhone");
            fieldMap.put("联系人","relatives");
            fieldMap.put("联系人电话","rPhone");
            fieldMap.put("状态","isGraduate");
            fieldMap.put("积分","score");
            List<Student>students=new ExcelHandle().handlerData(inputStream,fieldMap,Student.class);
         /*  students.forEach(System.out::println);
*/
           i=studentService.saveBatch(students);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(i>0)
        return RespBean.ok("上传成功");
        else
            return RespBean.error("上传失败");
    }
}
