package com.hr.service;

import com.hr.model.Active;
import com.hr.model.PageResult;

import java.util.List;

public interface ActiveService {
    public PageResult getAllActive(Integer page,Integer limit);
    public int saveActive(Active active);

    public List<Active> getThreeActive();
}
