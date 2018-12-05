package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.Customer;
import com.hz.service.CustomerService;
import com.hz.util.ResJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lyp
 * @date 2018/12/5
 */
@RestController
public class HzCustomerController extends BaseController {


    @Autowired
    CustomerService customerService;

    /**
     * 客户名称&&客户手机号查询
     * @param customer
     * @return
     */
    @RequestMapping(value = "/api/customer/getCustomerList")
    public String getCustomerList(Customer customer){
        ResJson resJson = new ResJson();

        List<Customer> customers = customerService.getCustomerList(customer,sysUser);
        resJson.setData(customers);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/customer/customerInfo")
    public String customerInfo(){
        ResJson resJson = new ResJson();
        return JSONObject.toJSONString(resJson);
    }
}
