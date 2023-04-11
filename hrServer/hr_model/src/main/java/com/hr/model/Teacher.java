package com.hr.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Teacher implements UserDetails {

    private String tName;

    private String tNo;

    private String tMajor;

    private String tPhone;

    private String tDepartment;

    private List<Role> roles; // 保存全部的角色
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Teacher(String tName, String tNo, String tMajor, String tPhone, String tDepartment) {
        this.tName = tName;
        this.tNo = tNo;
        this.tMajor = tMajor;
        this.tPhone = tPhone;
        this.tDepartment = tDepartment;
    }

    public Teacher() {
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo;
    }

    public String gettMajor() {
        return tMajor;
    }

    public void settMajor(String tMajor) {
        this.tMajor = tMajor;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettDepartment() {
        return tDepartment;
    }

    public void settDepartment(String tDepartment) {
        this.tDepartment = tDepartment;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "tName='" + tName + '\'' +
                ", tNo='" + tNo + '\'' +
                ", tMajor='" + tMajor + '\'' +
                ", tPhone='" + tPhone + '\'' +
                ", tDepartment='" + tDepartment + '\'' +
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
        return this.tNo;
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
