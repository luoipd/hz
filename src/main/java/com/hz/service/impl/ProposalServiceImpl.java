package com.hz.service.impl;

import com.hz.dao.AdvertisingProposalDetailMapper;
import com.hz.dao.AdvertisingProposalMapper;
import com.hz.dao.DataPicMapper;
import com.hz.domain.AdvertisingProposal;
import com.hz.domain.AdvertisingProposalDetail;
import com.hz.domain.DataPic;
import com.hz.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/22
 */
@Service
public class ProposalServiceImpl implements ProposalService {


    @Autowired
    AdvertisingProposalMapper advertisingProposalMapper;

    @Autowired
    DataPicMapper dataPicMapper;

    @Autowired
    AdvertisingProposalDetailMapper advertisingProposalDetailMapper;

    @Override
    public int createProposal(AdvertisingProposal advertisingProposal) {
        advertisingProposalMapper.insertSelective(advertisingProposal);
        return advertisingProposal.getId();

    }

    @Override
    public AdvertisingProposal selectProposal(int proposalId) {
        return advertisingProposalMapper.selectByPrimaryKey(proposalId);
    }

    @Override
    public void updateProposal(AdvertisingProposal advertisingProposal) {
        advertisingProposalMapper.updateByPrimaryKeySelective(advertisingProposal);
    }

    @Override
    public List<AdvertisingProposal> getProposalList(AdvertisingProposal advertisingProposal) {
        List<AdvertisingProposal> advertisingProposals = advertisingProposalMapper.selectProposalList(advertisingProposal);
        return advertisingProposals;
    }

    @Override
    public void deleteProposalById(int proposalId) {
        advertisingProposalMapper.deleteByPrimaryKey(proposalId);
    }

    /**
     * 方案查询资源表的数据
     * @param proposalId
     * @return
     */
    @Override
    public List getModuleInfoListByProposalId(int proposalId) {
        List<AdvertisingProposalDetail> advertisingProposalDetails = advertisingProposalDetailMapper.selectDataPicByProposalId(proposalId);
        for(AdvertisingProposalDetail advertisingProposalDetail:advertisingProposalDetails){
        }
        return null;
    }

}
