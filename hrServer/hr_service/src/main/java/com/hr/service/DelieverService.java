package com.hr.service;

import com.hr.model.Deliever;
import com.hr.model.PageResult;

import java.util.List;

public interface DelieverService {
    public int  addDelievet(Deliever deliever);

    public Deliever queryAllDeliever(String tno);

    public Deliever queryaccessDeliever(String sno);

    public Deliever querynoaccessDeliever(String sno);

    public Deliever querywaitDeliever(String sno);

    public Deliever queryOneStudentAllDeliever(String sno);


    public Deliever querySameaccessDeliever(String sno,String pid);

    public Deliever querySamenoaccessDeliever(String sno,String pid);

    public Deliever querySamewaitDeliever(String sno,String pid);

    public PageResult searchDelieverByPname(String title, Integer page, Integer limit);

    public  PageResult getAllDeliever(Integer page,Integer limit);

}
