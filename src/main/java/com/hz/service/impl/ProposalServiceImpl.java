package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.dao.*;
import com.hz.domain.*;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.service.ProposalService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
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

    @Autowired
    ContactUsMapper contactUsMapper;

    @Autowired
    CustomerCaseMapper customerCaseMapper;

    @Autowired
    UserMapper userMapper;


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
    public PageInfo<AdvertisingProposal> getProposalList(AdvertisingProposal advertisingProposal,boolean isDailishnag, boolean isAdmin, User sysUser, PageRequest pageRequest) {
        if(isDailishnag){
            List<Integer> users = userMapper.selectAllSaler(sysUser.getId());
            advertisingProposal.setCreaterIds(users);
            PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
            List<AdvertisingProposal> advertisingProposals = advertisingProposalMapper.selectProposalListByDailishang(advertisingProposal);
            PageInfo<AdvertisingProposal> advertisingProposalPageInfo = new PageInfo<>(advertisingProposals);
            return advertisingProposalPageInfo;
        }else {
            if(!isAdmin&&advertisingProposal.getCreaterId()==null){
                advertisingProposal.setCreaterId(sysUser.getId());
            }
            PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
            List<AdvertisingProposal> advertisingProposals = advertisingProposalMapper.selectProposalList(advertisingProposal);
            PageInfo<AdvertisingProposal> advertisingProposalPageInfo = new PageInfo<>(advertisingProposals);
            return advertisingProposalPageInfo;
        }



    }

    @Override
    public void deleteProposalById(int proposalId) {
        advertisingProposalMapper.deleteByPrimaryKey(proposalId);
    }

    @Override
    public void deleteProposalModule(AdvertisingProposalDetail advertisingProposalDetail) {
        advertisingProposalDetailMapper.deleteOldAdvertisingProposal(advertisingProposalDetail);
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

    @Override
    public void insertAdvertisingProposalDetail(AdvertisingProposalDetail advertisingProposalDetail,int id) {

        advertisingProposalDetailMapper.deleteOldAdvertisingProposal(advertisingProposalDetail);

        advertisingProposalDetail.setDataId(id);
        advertisingProposalDetailMapper.insertSelective(advertisingProposalDetail);
    }

    /**
     * 根据方案id生成一套新的方案版本（copied）版本历史纪录
     * @param proposalId
     */
    @Override
    @Transactional
    public void saveVersion(int proposalId,int createId) {
        String version = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        AdvertisingProposal advertisingProposal = advertisingProposalMapper.selectByPrimaryKey(proposalId);
        advertisingProposal.setVersion(version);
        List<AdvertisingProposalDetail> advertisingProposalDetails = advertisingProposalDetailMapper.selectListByParentId(proposalId);
        advertisingProposal.setCreaterId(createId);
        advertisingProposal.setId(null);
        advertisingProposalMapper.insertSelective(advertisingProposal);
        for(AdvertisingProposalDetail advertisingProposalDetail:advertisingProposalDetails){
            advertisingProposalDetail.setParentId(advertisingProposal.getId());
            advertisingProposalDetail.setCreaterId(createId);
            if(advertisingProposalDetail.getModuleType()==1){
                if(advertisingProposalDetail.getModuleId()==21){
                    advertisingProposalDetailMapper.insertSelective(advertisingProposalDetail);
                }else if(advertisingProposalDetail.getModuleId()==22){
                    ContactUs contactUs = contactUsMapper.selectByPrimaryKey(advertisingProposalDetail.getDataId());
                    contactUs.setCreaterId(createId);
                    contactUs.setId(null);
                    contactUsMapper.insertSelective(contactUs);
                    advertisingProposalDetail.setDataId(contactUs.getId());
                    advertisingProposalDetailMapper.insertSelective(advertisingProposalDetail);
                }else{
                    Home home = homeMapper.selectByPrimaryKey(advertisingProposalDetail.getDataId());
                    List<DataPic> dataPics = dataPicMapper.selectDataPicList(advertisingProposalDetail.getModuleId(),advertisingProposalDetail.getDataId());
                    home.setId(null);
                    home.setCreaterId(createId);
                    homeMapper.insertSelective(home);
                    advertisingProposalDetail.setDataId(home.getId());
                    advertisingProposalDetailMapper.insertSelective(advertisingProposalDetail);
                    if(dataPics.size()!=0){
                        for(DataPic dataPic:dataPics){
                            dataPic.setDataId(home.getId());
                            dataPic.setCreaterId(createId);
                            dataPicMapper.insertSelective(dataPic);
                        }
                    }
                }
            }else if(advertisingProposalDetail.getModuleType()==2){
                Market market = marketMapper.selectByPrimaryKey(advertisingProposalDetail.getDataId());
                List<DataPic> dataPics = dataPicMapper.selectDataPicList(advertisingProposalDetail.getModuleId(),advertisingProposalDetail.getDataId());
                market.setId(null);
                market.setCreaterId(createId);
                marketMapper.insertSelective(market);
                advertisingProposalDetail.setDataId(market.getId());
                advertisingProposalDetailMapper.insertSelective(advertisingProposalDetail);
                if(dataPics.size()!=0){
                    for(DataPic dataPic:dataPics){
                        dataPic.setDataId(market.getId());
                        dataPic.setCreaterId(createId);
                        dataPicMapper.insertSelective(dataPic);
                    }
                }
            }else if(advertisingProposalDetail.getModuleType()==3){
                advertisingProposalDetailMapper.insertSelective(advertisingProposalDetail);
            }
        }



    }

}
