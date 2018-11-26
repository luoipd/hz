package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.*;
import com.hz.domain.AdvertisingProposal;
import com.hz.domain.AdvertisingProposalDetail;
import com.hz.domain.Module;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.service.ProposalService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lyp
 * @date 2018/11/22
 */
@Service
public class ProposalServiceImpl implements ProposalService {


    @Autowired
    AdvertisingProposalMapper advertisingProposalMapper;
    @Autowired
    AdvertisingProposalDetailMapper advertisingProposalDetailMapper;

    @Autowired
    DataPicMapper dataPicMapper;

    @Autowired
    HomeMapper homeMapper;

    @Autowired
    MarketMapper marketMapper;

    @Autowired
    MethodResourceMapper methodResourceMapper;

    @Autowired
    ModuleMapper moduleMapper;


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
    public List<AdvertisingProposal> getProposalList(AdvertisingProposal advertisingProposal, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<AdvertisingProposal> advertisingProposals = advertisingProposalMapper.selectProposalList(advertisingProposal);
        return advertisingProposals;
    }

    @Override
    public void deleteProposalById(int proposalId) {
        advertisingProposalMapper.deleteByPrimaryKey(proposalId);
    }

    @Override
    public List<ProposalModuleBean> getModuleInfoListByProposalId(int proposalId) {
        List<ProposalModuleBean> proposalModuleBeans = new ArrayList<>();
        List<AdvertisingProposalDetail> advertisingProposalDetails = advertisingProposalDetailMapper.selectListByParentId(proposalId);
//        for(AdvertisingProposalDetail advertisingProposalDetail:advertisingProposalDetails){
//            ProposalModuleBean proposalModuleBean = new ProposalModuleBean();
//            proposalModuleBean.setModuleType(advertisingProposalDetail.getModuleType());
//            proposalModuleBean.setModuleId(advertisingProposalDetail.getModuleId());
//            proposalModuleBeans.add(proposalModuleBean);
//        }
//        proposalModuleBeans = removeDuplicateProposalModuleBean(proposalModuleBeans);
//        for(ProposalModuleBean proposalModuleBean:proposalModuleBeans){
//            List<Integer> dataIds = new ArrayList<>();
//            for(AdvertisingProposalDetail advertisingProposalDetail:advertisingProposalDetails){
//                if(advertisingProposalDetail.getModuleId()==proposalModuleBean.getModuleId()){
//                    dataIds.add(advertisingProposalDetail.getDataId());
//                }
//            }
//            Integer[] dataIds1 = new Integer[dataIds.size()];
//            dataIds.toArray(dataIds1);
//            proposalModuleBean.setDataIds(dataIds1);
//        }
        for(AdvertisingProposalDetail advertisingProposalDetail:advertisingProposalDetails){
            ProposalModuleBean proposalModuleBean = new ProposalModuleBean();
            proposalModuleBean.setModuleType(advertisingProposalDetail.getModuleType());
            proposalModuleBean.setPModuleId(advertisingProposalDetail.getpModuleId());
            proposalModuleBeans.add(proposalModuleBean);
        }
        return proposalModuleBeans;
    }

    private static List<ProposalModuleBean> removeDuplicateProposalModuleBean(List<ProposalModuleBean> proposalModuleBeans) {
        Set<ProposalModuleBean> set = new TreeSet<ProposalModuleBean>(new Comparator<ProposalModuleBean>() {
            @Override
            public int compare(ProposalModuleBean o1, ProposalModuleBean o2) {
                //字符串,则按照asicc码升序排列
                return o1.getModuleId().compareTo(o2.getModuleId());
            }
        });
        set.addAll(proposalModuleBeans);
        List<ProposalModuleBean> proposalModuleBeans1 = new ArrayList<>(set);
        return proposalModuleBeans1;
    }
    public static void main(String args[]){
        List<ProposalModuleBean> proposalModuleBeans = new ArrayList<>();
        ProposalModuleBean proposalModuleBean = new ProposalModuleBean();
        proposalModuleBean.setModuleId(1);
        proposalModuleBean.setModuleType(1);
        proposalModuleBeans.add(proposalModuleBean);
        ProposalModuleBean proposalModuleBean1 = new ProposalModuleBean();
        proposalModuleBean.setModuleId(1);
        proposalModuleBean.setModuleType(1);
        System.out.println(proposalModuleBeans.contains(proposalModuleBean1));

    }

    @Override
    public List<Module> getAllModuleList(int status) {
        return moduleMapper.selectAllModule(status);
    }

    @Override
    public int countProposalList(AdvertisingProposal advertisingProposal) {

        return advertisingProposalMapper.countProposalList(advertisingProposal);
    }

}
