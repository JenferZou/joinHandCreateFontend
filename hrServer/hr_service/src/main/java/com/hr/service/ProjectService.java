package com.hr.service;

import com.hr.model.PageResult;
import com.hr.model.Project;

public interface ProjectService {
    public PageResult getAllProject(Integer page,Integer limit);
    public int deleteOneById(Project project);
   public PageResult searchProject(String title,Integer page,Integer limit);
}
