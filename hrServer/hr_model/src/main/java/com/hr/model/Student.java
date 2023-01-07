package com.hr.model;
import java.lang.*;


/**
 * ClassName: Student
 * Description:
 * date: 2023/1/7 20:21
 *
 * @author: Jenf
 * @version:
 * @since JDK 17
 */
public class Student {

    private String sName;

    private String sMajor;

    private String sPhone;

    private String relatives;

    private String rPhone;

    private String mentor;

    private String star;

    private String blood;

    private String className;

    private String sDepartment;

    private Double score;

    private String sno;

    private String resumeId;


    public Student(String sName, String sMajor, String sPhone, String relatives, String rPhone, String mentor, String star, String blood, String className, String sDepartment, Double score, String sno, String resumeId) {
        this.sName = sName;
        this.sMajor = sMajor;
        this.sPhone = sPhone;
        this.relatives = relatives;
        this.rPhone = rPhone;
        this.mentor = mentor;
        this.star = star;
        this.blood = blood;
        this.className = className;
        this.sDepartment = sDepartment;
        this.score = score;
        this.sno = sno;
        this.resumeId = resumeId;
    }


    public Student() {
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

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getsDepartment() {
        return sDepartment;
    }

    public void setsDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sName='" + sName + '\'' +
                ", sMajor='" + sMajor + '\'' +
                ", sPhone='" + sPhone + '\'' +
                ", relatives='" + relatives + '\'' +
                ", rPhone='" + rPhone + '\'' +
                ", mentor='" + mentor + '\'' +
                ", star='" + star + '\'' +
                ", blood='" + blood + '\'' +
                ", className='" + className + '\'' +
                ", sDepartment='" + sDepartment + '\'' +
                ", score=" + score +
                ", sno='" + sno + '\'' +
                ", resumeId='" + resumeId + '\'' +
                '}';
    }



}