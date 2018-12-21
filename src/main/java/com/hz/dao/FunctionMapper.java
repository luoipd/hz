package com.hz.dao;

import com.hz.domain.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    List<Function> getFunctionsByRoleId(Integer id);

    List<Function> getFunctionsByRoleId1(Integer id);
    List<Function> getFunctionsByRolePid(@Param("id") Integer id, @Param("pId") Integer pId);


    List<Function> getFunctionsByRolePidPer(@Param("id") Integer id, @Param("pId") Integer pId);
    List<Function> getFunctionsByPid(int pId);

    List<Function> getFunctionsByPidPer(int pId);
}