package com.hz.service.impl;

import com.hz.dao.FunctionMapper;
import com.hz.dao.RoleFunctionMapper;
import com.hz.domain.Function;
import com.hz.domain.FunctionTreeBean;
import com.hz.domain.RoleFunction;
import com.hz.service.FunctionService;
import com.hz.util.FunctionTree;
import com.hz.util.TreeUtil;
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
    public List<FunctionTree> selectFunctionByRolePid(int roleId, int pId) {


        List<Function> functions = functionMapper.getFunctionsByRolePidPer(roleId,pId);
        List<Function> functions1 = functionMapper.getFunctionsByRolePid(roleId,pId);
        List<FunctionTree> functionTrees = functionTrees(functions,functions1);
        return functionTrees;
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
    public void editFunction(Function function) throws Exception {
        if(function.getId()==0||function.getId()==null){
            throw new Exception("没有修改id");
        }
        functionMapper.updateByPrimaryKeySelective(function);
    }

    @Override
    public List<FunctionTree> selectFunctionByPid(int pId) {
        List<Function> functions = functionMapper.getFunctionsByPidPer(pId);
        List<Function> functions1 = functionMapper.getFunctionsByPid(pId);
        List<FunctionTree> functionTrees = functionTrees(functions,functions1);
        return functionTrees;
    }

    public List<FunctionTree> functionTrees(List<Function> functions,List<Function> functions1){
        List<FunctionTree> list = toFunctionTree(functions);
        List<FunctionTree> list1 = toFunctionTree(functions1);
        TreeUtil utils =  new TreeUtil(list,list1);
        List<FunctionTree> functions2 = utils.getTree();
        return functions2;
    }

    public List<FunctionTree> toFunctionTree(List<Function> functions){
        List<FunctionTree> list = new ArrayList();
        for(Function function:functions){
            FunctionTree functionTree = new FunctionTree();
            functionTree.setId(String.valueOf(function.getId()));
            functionTree.setName(function.getFunctionName());
            functionTree.setPid(String.valueOf(function.getPid()));
            functionTree.setLevel(function.getLevel());
            list.add(functionTree);
        }
        return list;
    }

    @Override
    public List<Function> getFunctionList(int pId){
        List<Function> functions = functionMapper.getFunctionsByPidPer(pId);
        return functions;
    }

}
