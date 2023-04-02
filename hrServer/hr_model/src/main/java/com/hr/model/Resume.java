package com.hr.model;


public class Resume {

    private String resumeId;

    private String wechatId;

    private String politicsStatus;

    private String personalAdvantage;

    private String internshipExperience;

    private String projectExperience;

    private String awardExperience;

    private String certificate;

    public Resume() {
    }

    public Resume(String resumeId, String wechatId, String politicsStatus, String personalAdvantage, String internshipExperience, String projectExperience, String awardExperience, String certificate) {
        this.resumeId = resumeId;
        this.wechatId = wechatId;
        this.politicsStatus = politicsStatus;
        this.personalAdvantage = personalAdvantage;
        this.internshipExperience = internshipExperience;
        this.projectExperience = projectExperience;
        this.awardExperience = awardExperience;
        this.certificate = certificate;
    }


    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
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

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }


    @Override
    public String toString() {
        return "Resume{" +
                "resumeId='" + resumeId + '\'' +
                ", wechatId='" + wechatId + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", personalAdvantage='" + personalAdvantage + '\'' +
                ", internshipExperience='" + internshipExperience + '\'' +
                ", projectExperience='" + projectExperience + '\'' +
                ", awardExperience='" + awardExperience + '\'' +
                ", certificate='" + certificate + '\'' +
                '}';
    }
}
