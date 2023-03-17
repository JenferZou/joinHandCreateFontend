package com.hr.service;

import com.hr.model.Active;
import com.hr.model.Contest;
import com.hr.model.PageResult;

import java.util.List;

public interface ContestService {

    public PageResult getAllContestInformation(Integer page, Integer limit);
    public int deleteContest(Contest contest);
    public int addContest(Contest contest);
    public PageResult searchContest(String title,Integer page, Integer limit);
    public int removeMultiple(List<String> contestsIds);

    public List<Contest> getThreeContest();
}
