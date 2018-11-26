package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.*;
import com.hz.domain.responseBean.ProposalModuleBean;
import com.hz.service.CompanyResourceService;
import com.hz.service.ProposalService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public String getModuleInfo(ProposalModuleBean proposalModuleBean){
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



    @RequestMapping(value = "/api/hzProposal/insertModule" ,method = RequestMethod.POST)
    public String insertModule(){
        ResJson resJson = new ResJson();
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value="/api/hzProposal/getResourceListByModuleId" ,method = RequestMethod.GET)
    public String getResourceListByModuleId(){
        ResJson resJson = new ResJson();
        return JSONObject.toJSONString(resJson);
    }




}
