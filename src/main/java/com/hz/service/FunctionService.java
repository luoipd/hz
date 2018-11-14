package com.hz.service;

import com.hz.domain.Function;
import com.hz.domain.FunctionTreeBean;
import com.hz.domain.RoleFunction;
import com.hz.util.FunctionTree;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/1
 */
public interface FunctionService {


    List<Function> selectFunctionsByRoleId(Integer roleId);

    List<FunctionTree> selectFunctionByRolePid(int roleId,int pId);

    void insertFunction(RoleFunction roleFunction);

    void addFunction(Function function);

    void editFunction(Function function) throws Exception;

    List<FunctionTree> selectFunctionByPid(int pId);


    List<Function> getFunctionList(int pId);

}
