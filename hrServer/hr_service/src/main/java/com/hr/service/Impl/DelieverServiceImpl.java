package com.hr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.mapper.DelieverMapper;
import com.hr.model.Deliever;
import com.hr.model.PageResult;
import com.hr.model.Project;
import com.hr.service.DelieverService;
import com.hr.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelieverServiceImpl implements DelieverService {

    @Autowired
    private DelieverMapper delieverMapper;


    @Override
    public int addDelievet(Deliever deliever) {
        return delieverMapper.addDelievet(deliever);

    }

    @Override
    public Deliever queryAllDeliever(String tno) {
        return delieverMapper.queryAllDeliever(tno);
    }

    @Override
    public Deliever queryaccessDeliever(String sno) {
        return delieverMapper.queryaccessDeliever(sno);
    }

    @Override
    public Deliever querynoaccessDeliever(String sno) {
        return delieverMapper.querynoaccessDeliever(sno);
    }

    @Override
    public Deliever querywaitDeliever(String sno) {
        return delieverMapper.querywaitDeliever(sno);
    }

    @Override
    public Deliever queryOneStudentAllDeliever(String sno) {
        return delieverMapper.queryOneStudentAllDeliever(sno);
    }

    @Override
    public Deliever querySameaccessDeliever(String sno, String pid) {
        return delieverMapper.querySameaccessDeliever(sno,pid);
    }

    @Override
    public Deliever querySamenoaccessDeliever(String sno, String pid) {
        return delieverMapper.querySamenoaccessDeliever(sno, pid);
    }

    @Override
    public Deliever querySamewaitDeliever(String sno, String pid) {
        return delieverMapper.querySamewaitDeliever(sno, pid);
    }

    @Override
    public PageResult searchDelieverByPname(String title, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Deliever> delievers = delieverMapper.searchDelieverByPname(title);
        PageInfo<Deliever> pageInfo=new PageInfo<>(delievers);
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult getAllDeliever(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Deliever> allDeliever = delieverMapper.getAllDeliever();
        PageInfo<Deliever> pageInfo=new PageInfo<>(allDeliever);
        return PageUtil.getPageResult(pageInfo);

    }

    @Override
    public PageResult getwaitDelieverbytno(String tno, Integer page, Integer limit) {
        return null;
    }

    @Override
    public PageResult getaccessDelieverbytno(String tno, Integer page, Integer limit) {
        return null;
    }


}
