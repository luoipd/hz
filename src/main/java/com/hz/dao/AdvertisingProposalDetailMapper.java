package com.hz.dao;

import com.hz.domain.AdvertisingProposalDetail;
import com.hz.domain.responseBean.ProposalModuleBean;

import java.util.List;

public interface AdvertisingProposalDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisingProposalDetail record);

    int insertSelective(AdvertisingProposalDetail record);

    AdvertisingProposalDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertisingProposalDetail record);

    int updateByPrimaryKey(AdvertisingProposalDetail record);


    List<AdvertisingProposalDetail> selectListByParentId(int parentId);

    void deleteOldAdvertisingProposal(AdvertisingProposalDetail advertisingProposalDetail);


}