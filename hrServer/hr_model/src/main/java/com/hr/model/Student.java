package com.hr.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.*;
import java.util.Collection;
import java.util.List;


public class Student  implements UserDetails{

    private Integer id;

    private String sName;

    private String mentorPhone;

    public String getMentorPhone() {
        return mentorPhone;
    }

    public Student(Integer id, String sName, String mentorPhone, String gender, String isGraduate, String sMajor, String sPhone, String relatives, String rPhone, String mentor, String star, String blood, String className, String sDepartment, Double score, String sno, String resumeId) {
        this.id = id;
        this.sName = sName;
        this.mentorPhone = mentorPhone;
        this.gender = gender;
        this.isGraduate = isGraduate;
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

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsGraduate() {
        return isGraduate;
    }

    public void setIsGraduate(String isGraduate) {
        this.isGraduate = isGraduate;
    }

    private String gender;

    private String isGraduate;

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
    private String password;

    private List<Role> roles; // 保存全部的角色

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
                "id=" + id +
                ", sName='" + sName + '\'' +
                ", mentorPhone='" + mentorPhone + '\'' +
                ", gender='" + gender + '\'' +
                ", isGraduate='" + isGraduate + '\'' +
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
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", authorities=" + getAuthorities() +
                ", username='" + getUsername() + '\'' +
                ", accountNonExpired=" + isAccountNonExpired() +
                ", accountNonLocked=" + isAccountNonLocked() +
                ", credentialsNonExpired=" + isCredentialsNonExpired() +
                ", enabled=" + isEnabled() +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.sno;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}