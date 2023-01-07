package com.hr.model;

/**
 * ClassName: Manager
 * Description:
 * date: 2023/1/7 20:24
 *
 * @author: Jenf
 * @version:
 * @since JDK 17
 */
public class Manager {

    private String gName;

    private String gMajor;

    private String gNo;

    private String gPhone;

    private String gDepartment;

    public Manager(String gName, String gMajor, String gNo, String gPhone, String gDepartment) {
        this.gName = gName;
        this.gMajor = gMajor;
        this.gNo = gNo;
        this.gPhone = gPhone;
        this.gDepartment = gDepartment;
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
                '}';
    }
}
