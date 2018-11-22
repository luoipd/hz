package com.hz.service;

import com.hz.domain.AdvertisingProposal;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/22
 */
public interface ProposalService {

    int createProposal(AdvertisingProposal advertisingProposal);

    AdvertisingProposal selectProposal(int proposalId);

    void updateProposal(AdvertisingProposal advertisingProposal);

    List<AdvertisingProposal> getProposalList(AdvertisingProposal advertisingProposal);

    void deleteProposalById(int proposalId);

    List getModuleInfoListByProposalId(int proposalId);


}
