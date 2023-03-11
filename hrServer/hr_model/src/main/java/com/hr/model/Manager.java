package com.hr.model;


public class Manager {

    private String gName;

    private String gMajor;

    private String gNo;

    private String gPhone;

    private String gDepartment;
private int status;
private String remark;
private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Manager(String gName, String gMajor, String gNo, String gPhone, String gDepartment, int status, String remark, String role) {
        this.gName = gName;
        this.gMajor = gMajor;
        this.gNo = gNo;
        this.gPhone = gPhone;
        this.gDepartment = gDepartment;
        this.status = status;
        this.remark = remark;
        this.role = role;
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
                ", gPhone='" + gPhone + '\'' +
                ", gDepartment='" + gDepartment + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
