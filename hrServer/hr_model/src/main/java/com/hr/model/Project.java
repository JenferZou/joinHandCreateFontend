package com.hr.model;

public class Project {

    private String expectedCompetition;

    private String id;

    private String name;

    private String mentor;

    private String startTime;

    private String content;

    private String tno;

    private String needMajor;

    public Project() {
    }

    public Project(String expectedCompetition, String id, String name, String mentor, String startTime, String content, String tno, String needMajor) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
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
