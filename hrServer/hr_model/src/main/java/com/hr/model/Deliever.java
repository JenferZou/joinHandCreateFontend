package com.hr.model;

public class Deliever {

    private String id;

    private String sno;

    private String projectName;

    private String sName;

    private String sMajor;

    private String pid;

    private String tno;

    private String content;

    private Integer mark;

    private String mentor;


    public Deliever() {
    }

    public Deliever(String id, String sno, String projectName, String sName, String sMajor, String pid, String tno, String content, Integer mark, String mentor) {
        this.id = id;
        this.sno = sno;
        this.projectName = projectName;
        this.sName = sName;
        this.sMajor = sMajor;
        this.pid = pid;
        this.tno = tno;
        this.content = content;
        this.mark = mark;
        this.mentor = mentor;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsMajor() {
        return sMajor;
    }

    public void setsMajor(String sMajor) {
        this.sMajor = sMajor;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Deliever{" +
                "id='" + id + '\'' +
                ", sno='" + sno + '\'' +
                ", projectName='" + projectName + '\'' +
                ", sName='" + sName + '\'' +
                ", sMajor='" + sMajor + '\'' +
                ", pid='" + pid + '\'' +
                ", tno='" + tno + '\'' +
                ", content='" + content + '\'' +
                ", mark=" + mark +
                ", mentor='" + mentor + '\'' +
                '}';
    }
}
