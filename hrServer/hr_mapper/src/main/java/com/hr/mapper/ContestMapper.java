package com.hr.mapper;

import com.hr.model.Contest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ContestMapper {
    public List<Contest> queryAllContest();
    public int deleteContest(Contest contest);
    public int addContest(Contest contest);
    public List<Contest> queryContestByTitle(String title);
    public int removeMultiple(List<String> contestsIds);
}
