package com.hr.model;

/**
 * ClassName: Resume
 * Description:
 * date: 2023/1/7 21:51
 *
 * @author: Jenf
 * @version:
 * @since JDK 17
 */
public class Resume {

    private String resumeId;

    private String expectedPosition;

    private String expectedCity;

    private Double expectedSalary;

    private String wechatId;

    private String politicsStatus;

    private String personalAdvantage;

    private String educationExperience;

    private String internshipExperience;

    private String projectExperience;

    private String awardExperience;

    public Resume(String resumeId, String expectedPosition, String expectedCity, Double expectedSalary, String wechatId, String politicsStatus, String personalAdvantage, String educationExperience, String internshipExperience, String projectExperience, String awardExperience) {
        this.resumeId = resumeId;
        this.expectedPosition = expectedPosition;
        this.expectedCity = expectedCity;
        this.expectedSalary = expectedSalary;
        this.wechatId = wechatId;
        this.politicsStatus = politicsStatus;
        this.personalAdvantage = personalAdvantage;
        this.educationExperience = educationExperience;
        this.internshipExperience = internshipExperience;
        this.projectExperience = projectExperience;
        this.awardExperience = awardExperience;
    }

    public Resume() {
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getExpectedPosition() {
        return expectedPosition;
    }

    public void setExpectedPosition(String expectedPosition) {
        this.expectedPosition = expectedPosition;
    }

    public String getExpectedCity() {
        return expectedCity;
    }

    public void setExpectedCity(String expectedCity) {
        this.expectedCity = expectedCity;
    }

    public Double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getPersonalAdvantage() {
        return personalAdvantage;
    }

    public void setPersonalAdvantage(String personalAdvantage) {
        this.personalAdvantage = personalAdvantage;
    }

    public String getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }

    public String getInternshipExperience() {
        return internshipExperience;
    }

    public void setInternshipExperience(String internshipExperience) {
        this.internshipExperience = internshipExperience;
    }

    public String getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(String projectExperience) {
        this.projectExperience = projectExperience;
    }

    public String getAwardExperience() {
        return awardExperience;
    }

    public void setAwardExperience(String awardExperience) {
        this.awardExperience = awardExperience;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "resumeId='" + resumeId + '\'' +
                ", expectedPosition='" + expectedPosition + '\'' +
                ", expectedCity='" + expectedCity + '\'' +
                ", expectedSalary=" + expectedSalary +
                ", wechatId='" + wechatId + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", personalAdvantage='" + personalAdvantage + '\'' +
                ", educationExperience='" + educationExperience + '\'' +
                ", internshipExperience='" + internshipExperience + '\'' +
                ", projectExperience='" + projectExperience + '\'' +
                ", awardExperience='" + awardExperience + '\'' +
                '}';
    }
}
