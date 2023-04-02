package com.hr.model;

public class Project {

    private String id;

    private String name;

    private String mentor;

    private String startTime;

    private String content;

    private int needCount;

    private int resNumber;

    private int nowNumber;

    private String needMajor;

    public Project() {
    }


    public Project(String id, String name, String mentor, String startTime, String content, int needCount, int resNumber, int nowNumber, String needMajor) {
        this.id = id;
        this.name = name;
        this.mentor = mentor;
        this.startTime = startTime;
        this.content = content;
        this.needCount = needCount;
        this.resNumber = resNumber;
        this.nowNumber = nowNumber;
        this.needMajor = needMajor;
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

    public int getNeedCount() {
        return needCount;
    }

    public void setNeedCount(int needCount) {
        this.needCount = needCount;
    }

    public int getResNumber() {
        return resNumber;
    }

    public void setResNumber(int resNumber) {
        this.resNumber = resNumber;
    }

    public int getNowNumber() {
        return nowNumber;
    }

    public void setNowNumber(int nowNumber) {
        this.nowNumber = nowNumber;
    }

    public String getNeedMajor() {
        return needMajor;
    }

    public void setNeedMajor(String needMajor) {
        this.needMajor = needMajor;
    }



}
