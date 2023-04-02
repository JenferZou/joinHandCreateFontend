package com.hr.mapper;

import com.hr.model.Resume;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResumeMapper {
    public Resume getResumeByid(String resumeId);


}
