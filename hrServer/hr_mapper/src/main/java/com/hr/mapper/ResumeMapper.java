package com.hr.mapper;

import com.hr.model.Resume;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResumeMapper {
    public Resume getResumeByid(String resumeId);
//    public int updateWechat(String resumeId,String weChat);
//    public int updatepoliticsStatus(String resumeId,String politicsStatus);
//    public int updateinternshipExperience(String resumeId,String internshipExperience);
//    public int updateawardExperience(String resumeId,String awardExperience);
//    public int updateprojectExperience(String resumeId,String projectExperience);
//    public int updatecertificate(String resumeId,String certificate);

    public int updateAll(Resume resume);

    public int insertResume(String resumeId,String sno);
}
