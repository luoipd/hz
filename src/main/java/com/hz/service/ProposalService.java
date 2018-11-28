package com.hz.service;

import com.hz.domain.AdvertisingProposal;
import com.hz.domain.AdvertisingProposalDetail;
import com.hz.domain.Module;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/22
 */
public interface ProposalService {

    int createProposal(AdvertisingProposal advertisingProposal);

    AdvertisingProposal selectProposal(int proposalId);

    void updateProposal(AdvertisingProposal advertisingProposal);

    List<AdvertisingProposal> getProposalList(AdvertisingProposal advertisingProposal, PageRequest pageRequest);

    void deleteProposalById(int proposalId);

    void deleteProposalModule(AdvertisingProposalDetail advertisingProposalDetail);


    List<ProposalModuleBean> getModuleInfoListByProposalId(int proposalId);

    List<Module> getAllModuleList(int status);

    int countProposalList(AdvertisingProposal advertisingProposal);

    void insertAdvertisingProposalDetail(AdvertisingProposalDetail advertisingProposalDetail);



}
