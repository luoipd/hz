package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.dao.*;
import com.hz.domain.*;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.service.CompanyResourceService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/21
 */
@Service
@Transactional
public class CompanyResourceServiceImpl implements CompanyResourceService {

    @Autowired
    HomeMapper homeMapper;
    @Autowired
    DataPicMapper dataPicMapper;
    @Autowired
    PictureVideoMapper pictureVideoMapper;
    @Autowired
    CustomerCaseMapper customerCaseMapper;
    @Autowired
    ContactUsMapper contactUsMapper;
    @Autowired
    MarketMapper marketMapper;
    @Autowired
    MethodResourceMapper methodResourceMapper;
    @Autowired
    IndustryMapper industryMapper;
    @Autowired
    AdvertisingStandardDetailMapper advertisingStandardDetailMapper;
    @Autowired
    AdvertisingUnstandardDetailMapper advertisingUnstandardDetailMapper;
    @Autowired
    AdvertisingStyleMapper advertisingStyleMapper;
    @Autowired
    HuiBaoMapper huiBaoMapper;
    @Autowired
    MediaMapper mediaMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public PageInfo<Home> selectHomeList(Home home, PageRequest pageRequest) {
        home.setStatus(1);
        home.setProposalId(0);
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Home> homes = homeMapper.selectHomeList(home);
        for(Home home1:homes){
            List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(home1.getModuleId(),home1.getId());
            home1.setPictureVideos(pictureVideos);
        }
        PageInfo<Home> homePageInfo = new PageInfo<>(homes);
        return homePageInfo;
    }

    @Override
    public List<Home> selectHomeStenciList(Home home) {
        home.setStatus(1);
        home.setProposalId(0);
        List<Home> homes = homeMapper.selectHomeList(home);
        for(Home home1:homes){
            List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(home1.getModuleId(),home1.getId());
            home1.setPictureVideos(pictureVideos);
        }
        return homes;
    }

    @Override
    public PageInfo<Market> selectMarketList(Market market, PageRequest pageRequest) {
        market.setStatus(1);
        market.setProposalId(0);
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Market> markets = marketMapper.selectMarketList(market);
        for(Market market1:markets){
            List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(market1.getModuleId(),market1.getId());
            market1.setPictureVideos(pictureVideos);
        }
        PageInfo<Market> marketPageInfo = new PageInfo<>(markets);
        return marketPageInfo;
    }

    @Override
    public List<Market> selectMarketStenciList(Market market) {
        market.setStatus(1);
        market.setProposalId(0);
        List<Market> markets = marketMapper.selectMarketList(market);
        for(Market market1:markets){
            List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(market1.getModuleId(),market1.getId());
            market1.setPictureVideos(pictureVideos);
        }
        return markets;
    }

    @Override
    public int countHomeList(Home home) {
        home.setStatus(1);
        home.setProposalId(0);
        return homeMapper.countHomeList(home);
    }

    @Override
    public int countMarketList(Market market) {
        market.setStatus(1);
        market.setProposalId(0);
        return marketMapper.countMarketList(market);
    }

    @Override
    public int createHome(Home home, List<Integer> picIds, User user) {
        home.setStatus(1);
        home.setCreaterId(user.getId());
        homeMapper.insertSelective(home);
        insertDataPicHome(picIds,home,user);
        return home.getId();
    }

    @Override
    public void updateHome(Home home, List<Integer> picIds, User user) {
        home.setUpdaterId(user.getId());
        homeMapper.updateByPrimaryKeySelective(home);
        insertDataPicHome(picIds,home,user);

    }

    @Override
    public int createMarket(Market market, List<Integer> picIds, User user) {
        market.setStatus(1);
        marketMapper.insertSelective(market);
        insertDataPicMarket(picIds,market,user);
        return market.getId();
    }

    @Override
    public void updateMarket(Market market, List<Integer> picIds, User user) {
        market.setUpdaterId(user.getId());
        marketMapper.updateByPrimaryKeySelective(market);
        insertDataPicMarket(picIds,market,user);
    }

    @Override
    public void deleteDataPic(DataPic dataPic) {
        dataPicMapper.deleteDataPic(dataPic);
    }

    @Override
    public List<Home> getHomeInfo(ProposalModuleBean proposalModuleBean) {
        List<Home> homes = homeMapper.selectHomeAllModuleList(proposalModuleBean);
        for(Home home:homes){
            List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(home.getModuleId(),home.getId());
            home.setPictureVideos(pictureVideos);
        }
        return homes;
    }

    @Override
    public Home getHomeInfoById(int id) {
        Home home = homeMapper.selectByPrimaryKey(id);
        List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(home.getModuleId(),home.getId());
        home.setPictureVideos(pictureVideos);
        return home;
    }

    @Override
    public Market getMarketInfoById(int id) {
        Market market = marketMapper.selectByPrimaryKey(id);
        List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(market.getModuleId(),market.getId());
        market.setPictureVideos(pictureVideos);
        return market;
    }

    @Override
    public List<Market> getMarketInfo(ProposalModuleBean proposalModuleBean) {
        List<Market> markets = marketMapper.selectMarketAllModuleList(proposalModuleBean);
        for(Market market:markets){
            List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(market.getModuleId(),market.getId());
            market.setPictureVideos(pictureVideos);
        }
        return markets;
    }

    @Override
    public List<MethodResource> getMethodInfo(ProposalModuleBean proposalModuleBean) {
        List<MethodResource> methodResources = methodResourceMapper.selectMethodAllModuleList(proposalModuleBean);
        for(MethodResource methodResource:methodResources){
            methodResource.setMediaName(mediaMapper.selectByPrimaryKey(methodResource.getMediaId()).getMediaName());
            List<Tag> tagList = tagMapper.selectTagListByMethodId(methodResource.getId());
            methodResource.setTags(tagList);
            if(methodResource.getMethodType()==1){
                AdvertisingStandardDetail advertisingStandardDetail = advertisingStandardDetailMapper.selectByPid(methodResource.getId());
                methodResource.setAdvertisingStandardDetail(advertisingStandardDetail);
                List<AdvertisingStyle> advertisingStyles = advertisingStyleMapper.selectListByPid(methodResource.getId());
                methodResource.setAdvertisingStyles(advertisingStyles);

            }
            if(methodResource.getMethodType()==2){
                AdvertisingUnstandardDetail advertisingUnstandardDetail  = advertisingUnstandardDetailMapper.selectByPid(methodResource.getId());
                methodResource.setAdvertisingUnstandardDetail(advertisingUnstandardDetail);
                List<HuiBao> huiBaos = huiBaoMapper.selectListByPid(methodResource.getId());
                methodResource.setHuiBaos(huiBaos);
            }
        }
        return methodResources;
    }

    @Override
    public void insertCustomerCase(CustomerCase customerCase) {
        customerCaseMapper.insertSelective(customerCase);
    }

    @Override
    public void insertContactUs(ContactUs contactUs) {
        contactUsMapper.insertSelective(contactUs);
    }

    @Override
    public void updateContactUs(ContactUs contactUs) {
        contactUsMapper.updateByPrimaryKeySelective(contactUs);
    }

    @Override
    public List<Industry> getIndustryInfo() {

        return industryMapper.selectIndustryInfoList();
    }

    @Override
    public List<Customer> getCustomerList(Customer customer) {
        return customerMapper.selectCustomerList(customer);
    }

    @Override
    public ContactUs getContactUsByModule(ProposalModuleBean proposalModuleBean) {
        return contactUsMapper.getContactUsByModule(proposalModuleBean);
    }


    /**
     * 清除绑定图片再重新绑定
     * @param picIds
     * @param home
     * @param user
     */
    public void insertDataPicHome(List<Integer> picIds,Home home,User user){
        DataPic dataPic1 = new DataPic();
        dataPic1.setDataId(home.getId());
        dataPic1.setModuleId(home.getModuleId());
        dataPicMapper.deleteDataPic(dataPic1);
        for(int i :picIds){
            DataPic dataPic = new DataPic();
            dataPic.setDataId(home.getId());
            dataPic.setPicId(i);
            dataPic.setModuleId(home.getModuleId());
            dataPic.setUpdaterId(user.getId());
            dataPic.setCreaterId(user.getId());
            dataPicMapper.insertSelective(dataPic);
        }
    }

    public void insertDataPicMarket(List<Integer> picIds,Market market,User user){
        DataPic dataPic1 = new DataPic();
        dataPic1.setDataId(market.getId());
        dataPic1.setModuleId(market.getModuleId());
        dataPicMapper.deleteDataPic(dataPic1);
        for(int i :picIds){
            DataPic dataPic = new DataPic();
            dataPic.setDataId(market.getId());
            dataPic.setPicId(i);
            dataPic.setModuleId(market.getModuleId());
            dataPic.setUpdaterId(user.getId());
            dataPic.setCreaterId(user.getId());
            dataPicMapper.insertSelective(dataPic);
        }
    }

}
