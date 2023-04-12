package com.hr.mapper;

import com.hr.model.MemberRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    public int saveRole(MemberRole memberRole);
}
