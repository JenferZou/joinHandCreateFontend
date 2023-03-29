package com.hr.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Manager implements UserDetails {

    private String gName;

    private String gMajor;

    private String gNo;
    private String password; // 用户密码
    private String gPhone;

    private String gDepartment;
    private int status;
    private String remark;
    private List<Role> roles; // 保存全部的角色

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Manager(String gName, String gMajor, String gNo, String gPhone, String gDepartment, int status, String remark) {
        this.gName = gName;
        this.gMajor = gMajor;
        this.gNo = gNo;
        this.gPhone = gPhone;
        this.gDepartment = gDepartment;
        this.status = status;
        this.remark = remark;
    }

    public Manager() {
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgMajor() {
        return gMajor;
    }

    public void setgMajor(String gMajor) {
        this.gMajor = gMajor;
    }

    public String getgNo() {
        return gNo;
    }

    public void setgNo(String gNo) {
        this.gNo = gNo;
    }

    public String getgPhone() {
        return gPhone;
    }

    public void setgPhone(String gPhone) {
        this.gPhone = gPhone;
    }

    public String getgDepartment() {
        return gDepartment;
    }

    public void setgDepartment(String gDepartment) {
        this.gDepartment = gDepartment;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "gName='" + gName + '\'' +
                ", gMajor='" + gMajor + '\'' +
                ", gNo='" + gNo + '\'' +
                ", password='" + password + '\'' +
                ", gPhone='" + gPhone + '\'' +
                ", gDepartment='" + gDepartment + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
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
        return this.gNo;
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
