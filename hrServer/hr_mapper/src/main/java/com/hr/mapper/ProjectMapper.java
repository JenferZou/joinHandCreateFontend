package com.hr.mapper;

import com.hr.model.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProjectMapper {
    public List<Project> getAllProject();
    public int deleteOneById(Project project);
    public List<Project> queryProjectByTitle(String title);
}
