package com.hr.mapper;

import com.hr.model.Deliever;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DelieverMapper {
    public int  addDelievet(Deliever deliever);

    public Deliever queryAllDeliever(String tno);

    public Deliever queryaccessDeliever(String sno);

    public Deliever querynoaccessDeliever(String sno);

    public Deliever querywaitDeliever(String sno);

    public Deliever querySameaccessDeliever(@Param("sno") String sno, @Param("pid")String pid);

    public Deliever querySamenoaccessDeliever(@Param("sno")String sno,@Param("pid")String pid);

    public Deliever querySamewaitDeliever(@Param("sno")String sno,@Param("pid")String pid);

    public Deliever queryOneStudentAllDeliever(String sno);

    public List<Deliever> searchDelieverByPname(String title);

    public List<Deliever> getAllDeliever();

    public List<Deliever> getwaitDelieverbytno(String tno);

    public List<Deliever> getaccessDelieverbytno(String tno);

    public int agreeDeliever(Deliever deliever);

    public int refuseDeliever(Deliever deliever);

    public List<Deliever> getaDelieverbyname(String name);


    public List<Deliever> getaccessDelieverbytnoandpid(@Param("tno") String tno,@Param("pid") String pid);

}
