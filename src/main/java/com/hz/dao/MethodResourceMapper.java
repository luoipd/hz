package com.hz.dao;

import com.hz.domain.MethodResource;
import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.List;

public interface MethodResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MethodResource record);

    int insertSelective(MethodResource record);

    MethodResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MethodResource record);

    int updateByPrimaryKey(MethodResource record);

    List<MethodResource> selectResourceList(MethodResource methodResource);

    int countResources(MethodResource methodResource);

    List<MethodResource> selectMethodAllModuleList(ProposalModuleBean proposalModuleBean);
}