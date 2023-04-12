package com.hr.service;

import com.hr.model.PageResult;
import com.hr.model.Project;

import java.util.List;

public interface ProjectService {
    public PageResult getAllProject(Integer page,Integer limit);
    public int deleteOneById(Project project);
   public PageResult searchProject(String title,Integer page,Integer limit);
   public List<Project> getThreeProject();
   public int saveProject(Project project);
}
