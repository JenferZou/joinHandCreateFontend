package com.hr.service.Impl;

import com.hr.mapper.ResumeMapper;
import com.hr.model.Resume;
import com.hr.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImp implements ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public Resume getResumeByid(String resumeId) {
        return resumeMapper.getResumeByid(resumeId);
    }


    @Override
    public int updateAll(Resume resume) {
        return resumeMapper.updateAll(resume);
    }

    @Override
    public int insertResume(String resumeId, String sno) {
         return resumeMapper.insertResume(resumeId,sno);
    }
}
