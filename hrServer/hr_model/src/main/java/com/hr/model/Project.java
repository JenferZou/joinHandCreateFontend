package com.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Project {

    private String expectedCompetition;

    private String id;

    private String name;

    private String mentor;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date startTime;

    private String content;

    private String tno;

    private String needMajor;

    public Project() {
    }

    public Project(String expectedCompetition, String id, String name, String mentor, Date startTime, String content, String tno, String needMajor) {
        this.expectedCompetition = expectedCompetition;
        this.id = id;
        this.name = name;
        this.mentor = mentor;
        this.startTime = startTime;
        this.content = content;
        this.tno = tno;
        this.needMajor = needMajor;
    }

    public String getExpectedCompetition() {
        return expectedCompetition;
    }

    public void setExpectedCompetition(String expectedCompetition) {
        this.expectedCompetition = expectedCompetition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getNeedMajor() {
        return needMajor;
    }

    public void setNeedMajor(String needMajor) {
        this.needMajor = needMajor;
    }
}
