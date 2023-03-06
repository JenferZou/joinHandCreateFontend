package com.hr.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.mapper.ContestMapper;
import com.hr.model.Contest;

import com.hr.model.PageResult;
import com.hr.model.Student;
import com.hr.service.ContestService;
import com.hr.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContestServiceImpl implements ContestService {
    @Autowired
    private ContestMapper contestMapper;
    @Override
    public PageResult getAllContestInformation(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Contest> contests=contestMapper.queryAllContest();
        PageInfo<Contest> pageInfo=new PageInfo<>(contests);
        return PageUtil.getPageResult(pageInfo);
    }


}
