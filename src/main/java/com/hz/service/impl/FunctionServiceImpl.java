package com.hz.service.impl;

import com.hz.dao.FunctionMapper;
import com.hz.dao.RoleFunctionMapper;
import com.hz.domain.Function;
import com.hz.domain.FunctionTreeBean;
import com.hz.domain.RoleFunction;
import com.hz.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<FunctionTreeBean> selectFunctionByPid(int pId) {

        List<Function> functions = functionMapper.getFunctionsByPid(pId);
        List<FunctionTreeBean> list2 =new ArrayList<FunctionTreeBean>();
        for(Function function:functions)  {
            FunctionTreeBean functionTreeBean = new FunctionTreeBean();
            functionTreeBean.setId(function.getId());
            functionTreeBean.setPId(function.getPid());
            functionTreeBean.setName(function.getFunctionName());
            list2.add(functionTreeBean);
        }


//        Function function = functionMapper.selectByPrimaryKey(pId);
//        List<Function> functions =  functionMapper.getFunctionsByPidPer(pId);
//        for(Function function1:functions){
//            List<Function> functions1 = functionMapper.getFunctionsByPidPer(function1.getId());
//            function1.setChildFunctions(functions1);
//        }
//        function.setChildFunctions(functions);
        return list2;
    }

}
