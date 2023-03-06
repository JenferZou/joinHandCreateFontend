package com.hr.service;

import com.hr.model.Contest;
import com.hr.model.PageResult;

import java.util.List;

public interface ContestService {

    public PageResult getAllContestInformation(Integer page, Integer limit);
}
