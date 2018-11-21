package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.domain.Home;
import com.hz.service.CompanyResourceService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lyp
 * @date 2018/11/21
 */
@RestController
public class HzCompanyResourceController {


    @Autowired
    CompanyResourceService companyResourceService;

    @RequestMapping(value = "/api/companyResource/homeList",method = RequestMethod.GET)
    @ResponseBody
    public String homeList(@Valid Home home, @Valid PageRequest pageRequest){
        ResJson resJson = new ResJson();
        companyResourceService.selectHomeList(home,pageRequest);
        return JSONObject.toJSONString(resJson);
    }



}
