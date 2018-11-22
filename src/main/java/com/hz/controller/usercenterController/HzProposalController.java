package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.AdvertisingProposal;
import com.hz.service.ProposalService;
import com.hz.util.ResJson;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 方案管理模块
 * @author lyp
 * @date 2018/11/21
 */
@RestController
public class HzProposalController extends BaseController {

    @Autowired
    ProposalService proposalService;

    @RequestMapping(value = "/api/hzProposal/proposalList" ,method = RequestMethod.GET)
    public String proposalList(@Valid AdvertisingProposal advertisingProposal){
        ResJson resJson = new ResJson();
        List<AdvertisingProposal> advertisingProposals = proposalService.getProposalList(advertisingProposal);
        resJson.setData(advertisingProposals);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzProposal/createProposal",method = RequestMethod.POST)
    public String createProposal(@Valid AdvertisingProposal advertisingProposal){
        ResJson resJson = new ResJson();
        if(advertisingProposal.getCustomerId()==null){
            resJson.setDesc("未选择客户!!!");
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

    @RequestMapping(value = "/api/hzProposal/getModuleInfoList" ,method = RequestMethod.GET)
    public String getModuleInfoList(){
        ResJson resJson = new ResJson();
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
