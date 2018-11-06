package com.hz.service.impl;

import com.hz.dao.FunctionMapper;
import com.hz.dao.RoleFunctionMapper;
import com.hz.domain.Function;
import com.hz.domain.RoleFunction;
import com.hz.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/1
 */
@Service
public class FunctionServiceImpl implements FunctionService {


    @Autowired
    FunctionMapper functionMapper;
    @Autowired
    RoleFunctionMapper roleFunctionMapper;

    @Override
    public List<Function> selectFunctionsByRoleId(Integer roleId) {
        return functionMapper.getFunctionsByRoleId(roleId);
    }

    @Override
    public List<Function> selectFunctionByRolePid(int roleId, int pId) {
        return functionMapper.getFunctionsByRolePid(roleId,pId);
    }

    @Override
    public void insertFunction(RoleFunction roleFunction) {
        /*
        查询出所有的功能模块
         */
        List<Function> functions = functionMapper.getFunctionsByPid(roleFunction.getFunctionId());
//        逐条插入
        for(Function function:functions){
            RoleFunction roleFunction1 = new RoleFunction();
            roleFunction1.setFunctionId(function.getId());
            roleFunction1.setRoleId(roleFunction.getRoleId());
            roleFunctionMapper.insertSelective(roleFunction1);
        }
    }

    @Override
    public void addFunction(Function function) {
        functionMapper.insertSelective(function);
    }

    @Override
    public void editFunction(Function function) {
        functionMapper.updateByPrimaryKeySelective(function);
    }

}
