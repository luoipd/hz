package com.hz.service;

import com.hz.domain.Function;
import com.hz.domain.FunctionTreeBean;
import com.hz.domain.RoleFunction;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/1
 */
public interface FunctionService {


    List<Function> selectFunctionsByRoleId(Integer roleId);

    List<Function> selectFunctionByRolePid(int roleId,int pId);

    void insertFunction(RoleFunction roleFunction);

    void addFunction(Function function);

    void editFunction(Function function);

    List<FunctionTreeBean> selectFunctionByPid(int pId);

}
