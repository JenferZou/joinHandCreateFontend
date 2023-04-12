package com.hr.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Teacher implements UserDetails {

    private String name;

    private String no;

    private String major;

    private String phone;

    private String department;

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


    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tName='" + name + '\'' +
                ", tNo='" + no + '\'' +
                ", tMajor='" + major + '\'' +
                ", tPhone='" + phone + '\'' +
                ", tDepartment='" + department + '\'' +
                ", roles=" + roles +
                ", password='" + password + '\'' +
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
        return this.no;
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
