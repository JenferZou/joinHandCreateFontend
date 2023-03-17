package com.hr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.mapper.ActiveMapper;
import com.hr.model.Active;
import com.hr.model.Manager;
import com.hr.model.PageResult;
import com.hr.service.ActiveService;
import com.hr.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    private ActiveMapper activeMapper;
    @Override
    public PageResult getAllActive(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Active> actives=activeMapper.getAllActive();
        PageInfo<Active> pageInfo=new PageInfo<>(actives);
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public int saveActive(Active active) {
        active.setId(UUID.randomUUID().toString().substring(0,10));
        return activeMapper.saveActive(active);
    }

    @Override
    public int deleteOneById(Active active) {
        return activeMapper.deleteOneById(active);
    }
}
