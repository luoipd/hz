package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.*;
import com.hz.domain.responseBean.HomeParamBean;
import com.hz.domain.responseBean.MarketParamBean;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.service.CompanyResourceService;
import com.hz.service.PictureVideoService;
import com.hz.service.ProposalService;
import com.hz.service.impl.ImageService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方案管理模块
 * @author lyp
 * @date 2018/11/21
 */
@RestController
public class HzProposalController extends BaseController {

    @Autowired
    ProposalService proposalService;
    @Autowired
    CompanyResourceService companyResourceService;
    @Autowired
    ImageService imageService;
    @Autowired
    PictureVideoService pictureVideoService;

    @RequestMapping(value = "/api/hzProposal/proposalList" ,method = RequestMethod.GET)
    public String proposalList(@Valid AdvertisingProposal advertisingProposal, @Valid PageRequest pageRequest){
        ResJson resJson = new ResJson();
        List<AdvertisingProposal> advertisingProposals = proposalService.getProposalList(advertisingProposal,pageRequest);
        int count = proposalService.countProposalList(advertisingProposal);
        Map map = new HashMap();
        map.put("advertisingProposals",advertisingProposals);
        map.put("total",count);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzProposal/createProposal",method = RequestMethod.POST)
    public String createProposal(@Valid AdvertisingProposal advertisingProposal){
        ResJson resJson = new ResJson();
        if(advertisingProposal.getCustomerId()==null||advertisingProposal.getIndustryId()==null||advertisingProposal.getThemeId()==null){
            resJson.setDesc("请选择客户、主题和行业！！！");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }

        advertisingProposal.setCreaterId(sysUser.getId());
        int id = proposalService.createProposal(advertisingProposal);
        resJson.setData(id);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzProposal/proposalInfo" ,method = RequestMethod.GET)
    public String proposalInfo(@Valid int proposalId){
        ResJson resJson = new ResJson();
        AdvertisingProposal advertisingProposal = proposalService.selectProposal(proposalId);
        List<ProposalModuleBean> list = proposalService.getModuleInfoListByProposalId(proposalId);
        advertisingProposal.setProposalModuleBeans(list);
        resJson.setData(advertisingProposal);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzProposal/updateProposal" ,method = RequestMethod.POST)
    public String updateProposal(@Valid AdvertisingProposal advertisingProposal){
        ResJson resJson = new ResJson();
        if(advertisingProposal.getId()==0){
            resJson.setStatus(0);
            resJson.setDesc("缺少id");
            return JSONObject.toJSONString(resJson);
        }
        advertisingProposal.setUpdaterId(sysUser.getId());
        proposalService.updateProposal(advertisingProposal);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzProposal/delProposal" ,method = RequestMethod.POST)
    public String delProposal(int id){
        ResJson resJson = new ResJson();
        proposalService.deleteProposalById(id);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 获取已编辑的模块
     * @param proposalId
     * @return
     */
    @RequestMapping(value = "/api/hzProposal/getModuleCheckedList" ,method = RequestMethod.GET)
    public String getModuleInfoList(@Valid int proposalId){
        ResJson resJson = new ResJson();

        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzProposal/getAllModuleList")
    public String getAllModuleList(){
        ResJson resJson = new ResJson();
        List<Module> modules = proposalService.getAllModuleList(1);
        resJson.setData(modules);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 根据传入的moduleType ,pModuleId,dataId 获取对应的信息
     * @param
     * @return
     */
    @RequestMapping(value = "/api/hzProposal/getModuleInfo")
    public String getModuleInfo(@Valid ProposalModuleBean proposalModuleBean){
        ResJson resJson = new ResJson();
        /**
         * 公司介绍
         */
        if(proposalModuleBean.getModuleType()==1){
            List<Home> homes = companyResourceService.getHomeInfo(proposalModuleBean);
            resJson.setData(homes);
            //联系我们
        }
        if(proposalModuleBean.getModuleType()==2){
            List<Market> markets = companyResourceService.getMarketInfo(proposalModuleBean);
            resJson.setData(markets);
        }
        if(proposalModuleBean.getModuleType()==3){
            List<MethodResource> methodResources = companyResourceService.getMethodInfo(proposalModuleBean);
            resJson.setData(methodResources);
        }
        return JSONObject.toJSONString(resJson);
    }
    /**
     * 板块一配置资源&&更新资料
     * @return
     */
    @RequestMapping(value = "/api/hzProposal/insertHomeModule" ,method = RequestMethod.POST)
    public String insertModule(@Valid HomeParamBean homeParamBean, @RequestParam("files") MultipartFile[] files,
                               @Valid Integer[] picIds
                               ){
        ResJson resJson = new ResJson();
        AdvertisingProposalDetail advertisingProposalDetail = new AdvertisingProposalDetail();
        advertisingProposalDetail.setDataId(homeParamBean.getDataId());
        advertisingProposalDetail.setModuleId(homeParamBean.getModuleId());
        advertisingProposalDetail.setParentId(homeParamBean.getParentId());
        advertisingProposalDetail.setpModuleId(homeParamBean.getPModuleId());
        advertisingProposalDetail.setModuleType(homeParamBean.getModuleType());
        //上传图片
        List<Integer> list = imageService.insertPictureFiles(files,pictureVideoService,sysUser);
        if(picIds!=null&&picIds.length!=0){
            list.addAll(Arrays.asList(picIds));
        }

        Home home = new Home();
        home.setStatus(1);
        home.setModuleId(homeParamBean.getModuleId());
        home.setProposalId(homeParamBean.getParentId());
        home.setContent(homeParamBean.getContent());
        home.setTitle(homeParamBean.getTitle());
        home.setCreaterId(sysUser.getId());

        if(homeParamBean.getDataId()==null){
            int id = companyResourceService.createHome(home,list,sysUser);
            //客户案例
            if(home.getModuleId()==21){
                CustomerCase customerCase = new CustomerCase();
                customerCase.setAdvertisingType(homeParamBean.getAdvertisingType());
                customerCase.setCustomerName(homeParamBean.getCustomerName());
                customerCase.setIndustryId(homeParamBean.getIndustryId());
                customerCase.setTitle(homeParamBean.getTitle());
                customerCase.setModuleId(homeParamBean.getModuleId());
                customerCase.setProposalId(homeParamBean.getParentId());
                customerCase.setParentId(id);
                customerCase.setCreaterId(sysUser.getId());
                companyResourceService.insertCustomerCase(customerCase);
            }
            //联系我们
            if(home.getModuleId()==22){
                ContactUs contactUs = new ContactUs();
                contactUs.setAddress(homeParamBean.getAddress());
                contactUs.setCreaterId(sysUser.getId());
                contactUs.setEmail(homeParamBean.getEmail());
                contactUs.setModuleId(homeParamBean.getModuleId());
                contactUs.setProposalId(homeParamBean.getParentId());
                contactUs.setPhone(homeParamBean.getPhone());
                contactUs.setVxId(homeParamBean.getVxId());
                contactUs.setTitle(homeParamBean.getTitle());
                contactUs.setStatus("1");
                contactUs.setParentId(id);
                contactUs.setWebsits(homeParamBean.getWebsits());
                contactUs.setParentId(id);
                companyResourceService.insertContactUs(contactUs);
            }
            advertisingProposalDetail.setDataId(id);
            if(advertisingProposalDetail.getModuleId()==null){
                resJson.setStatus(0);
                resJson.setDesc("缺少模块id");
            }else if(advertisingProposalDetail.getModuleType()==null){
                resJson.setStatus(0);
                resJson.setDesc("缺少模块类型");
            }else if(advertisingProposalDetail.getParentId()==null){
                resJson.setDesc("缺少方案id");
                resJson.setStatus(0);
            }else if(advertisingProposalDetail.getDataId()==null){
                resJson.setDesc("缺少资源id");
                resJson.setStatus(0);
            }else if(advertisingProposalDetail.getpModuleId()==null){
                resJson.setDesc("缺少子模块id");
                resJson.setStatus(0);
            }else{
                advertisingProposalDetail.setCreaterId(sysUser.getId());
                proposalService.insertAdvertisingProposalDetail(advertisingProposalDetail);
            }
        }else{
            home.setId(homeParamBean.getDataId());
            companyResourceService.updateHome(home,list,sysUser);
        }
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 板块二配置资源&&更新资料
     * @return
     */
    @RequestMapping(value = "/api/hzProposal/insertMarketModule" ,method = RequestMethod.POST)
    public String insertModule(@Valid MarketParamBean marketParamBean, @RequestParam("files") MultipartFile[] files,
                               @Valid Integer[] picIds
    ){
        ResJson resJson = new ResJson();
        AdvertisingProposalDetail advertisingProposalDetail = new AdvertisingProposalDetail();
        advertisingProposalDetail.setDataId(marketParamBean.getDataId());
        advertisingProposalDetail.setModuleId(marketParamBean.getModuleId());
        advertisingProposalDetail.setParentId(marketParamBean.getParentId());
        advertisingProposalDetail.setpModuleId(marketParamBean.getPModuleId());
        advertisingProposalDetail.setModuleType(marketParamBean.getModuleType());
        //上传图片
        List<Integer> list = imageService.insertPictureFiles(files,pictureVideoService,sysUser);
        if(picIds!=null&&picIds.length!=0){
            list.addAll(Arrays.asList(picIds));
        }

        Market market = new Market();
        market.setStatus(1);
        market.setContent(marketParamBean.getContent());
        market.setIndustryId(marketParamBean.getIndustryId());
        market.setModuleId(marketParamBean.getModuleId());
        market.setProposalId(marketParamBean.getParentId());
        market.setTitle(marketParamBean.getTitle());
        market.setCreaterId(sysUser.getId());

        if(marketParamBean.getDataId()==null){
            int id = companyResourceService.createMarket(market,list,sysUser);
            advertisingProposalDetail.setDataId(id);
            if(advertisingProposalDetail.getModuleId()==null){
                resJson.setStatus(0);
                resJson.setDesc("缺少模块id");
            }else if(advertisingProposalDetail.getModuleType()==null){
                resJson.setStatus(0);
                resJson.setDesc("缺少模块类型");
            }else if(advertisingProposalDetail.getParentId()==null){
                resJson.setDesc("缺少方案id");
                resJson.setStatus(0);
            }else if(advertisingProposalDetail.getDataId()==null){
                resJson.setDesc("缺少资源id");
                resJson.setStatus(0);
            }else if(advertisingProposalDetail.getpModuleId()==null){
                resJson.setDesc("缺少子模块id");
                resJson.setStatus(0);
            }else{
                advertisingProposalDetail.setCreaterId(sysUser.getId());
                proposalService.insertAdvertisingProposalDetail(advertisingProposalDetail);
            }
        }else{
            market.setId(marketParamBean.getDataId());
            companyResourceService.updateMarket(market,list,sysUser);
        }
        return JSONObject.toJSONString(resJson);
    }



    @RequestMapping(value = "/api/hzProposal/delModule",method = RequestMethod.POST)
    public String delModule(){
        ResJson resJson = new ResJson();
        return JSONObject.toJSONString(resJson);
    }


    @RequestMapping(value="/api/hzProposal/getResourceListByModuleId" ,method = RequestMethod.GET)
    public String getResourceListByModuleId(){
        ResJson resJson = new ResJson();

        return JSONObject.toJSONString(resJson);
    }




}
