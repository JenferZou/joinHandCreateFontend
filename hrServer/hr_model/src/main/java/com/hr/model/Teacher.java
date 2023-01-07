package com.hr.model;

/**
 * ClassName: Teacher
 * Description:
 * date: 2023/1/7 20:21
 *
 * @author: Jenf
 * @version:
 * @since JDK 17
 */
public class Teacher {

    private String tName;

    private String tNo;

    private String tMajor;

    private String tPhone;

    private String tDepartment;

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



}
