package com.hr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.mapper.ProjectMapper;
import com.hr.model.Contest;
import com.hr.model.PageResult;
import com.hr.model.Project;
import com.hr.model.Student;
import com.hr.service.ProjectService;
import com.hr.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public PageResult getAllProject(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Project> projects=projectMapper.getAllProject();
        PageInfo<Project> pageInfo=new PageInfo<>(projects);
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public int deleteOneById(Project project) {
        return projectMapper.deleteOneById(project);
    }

    @Override
    public PageResult searchProject(String title,Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Project> projects=projectMapper.queryProjectByTitle(title);
        PageInfo<Project> pageInfo=new PageInfo<>(projects);
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public List<Project> getThreeProject() {
        return projectMapper.getThreeProject();
    }

    @Override
    public int saveProject(Project project) {
        project.setId(UUID.randomUUID().toString().substring(0,10));
        return projectMapper.saveProject(project);
    }
}
