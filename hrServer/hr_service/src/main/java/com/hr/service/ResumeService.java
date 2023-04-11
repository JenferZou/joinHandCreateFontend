package com.hr.service;

import com.hr.model.Resume;

public interface ResumeService {
    public Resume getResumeByid(String resumeId);


    public int updateAll(Resume resume);

    public int insertResume(String resumeId,String sno);

}
