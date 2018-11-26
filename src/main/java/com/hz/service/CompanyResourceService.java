package com.hz.service;

import com.hz.domain.*;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/21
 */
public interface CompanyResourceService {

    List<Home> selectHomeList(Home home, PageRequest pageRequest);

    int countHomeList(Home home);

    int createHome(Home home, List<Integer> picIds, User user);

    void updateHome(Home home,List<Integer> picIds,User user);

    void deleteDataPic(DataPic dataPic);

    List<Home> getHomeInfo(ProposalModuleBean proposalModuleBean);

    List<Market> getMarketInfo(ProposalModuleBean proposalModuleBean);

    List<MethodResource> getMethodInfo(ProposalModuleBean proposalModuleBean);
}
