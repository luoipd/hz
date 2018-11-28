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

    List<Home> selectHomeStenciList(Home home);

    List<Market> selectMarketList(Market market,PageRequest pageRequest);

    List<Market> selectMarketStenciList(Market market);

    int countHomeList(Home home);

    int countMarketList(Market market);

    int createHome(Home home, List<Integer> picIds, User user);

    void updateHome(Home home,List<Integer> picIds,User user);

    int createMarket(Market market, List<Integer> picIds, User user);

    void updateMarket(Market market,List<Integer> picIds,User user);

    void deleteDataPic(DataPic dataPic);

    List<Home> getHomeInfo(ProposalModuleBean proposalModuleBean);

    Home getHomeInfoById(int id);

    Market getMarketInfoById(int id);

    List<Market> getMarketInfo(ProposalModuleBean proposalModuleBean);

    List<MethodResource> getMethodInfo(ProposalModuleBean proposalModuleBean);

    void insertCustomerCase(CustomerCase customerCase);

    void insertContactUs(ContactUs contactUs);


}
