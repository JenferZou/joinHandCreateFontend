package com.hr.mapper;

import com.hr.model.Active;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ActiveMapper {
    public List<Active> getAllActive();
    public int saveActive(Active active);

    public List<Active>getThreeActive();
    public int deleteOneById(Active active);
}
