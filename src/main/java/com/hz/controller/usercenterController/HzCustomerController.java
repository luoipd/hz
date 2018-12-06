package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.Customer;
import com.hz.domain.Role;
import com.hz.service.CustomerService;
import com.hz.service.PictureVideoService;
import com.hz.service.impl.ImageService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyp
 * @date 2018/12/5
 */
@RestController
@Slf4j
public class HzCustomerController extends BaseController {


    @Autowired
    CustomerService customerService;
    @Autowired
    ImageService imageService;
    @Autowired
    PictureVideoService pictureVideoService;

    /**
     * 客户名称&&客户手机号查询(代理商&&销售列表)
     * @param customer
     * @return
     */
    @RequestMapping(value = "/api/customer/getCustomerList",method = RequestMethod.GET)
    public String getCustomerList(@Valid Customer customer, @Valid PageRequest pageRequest){
        ResJson resJson = new ResJson();
        List<Role> roles = sysUser.getRoles();
        boolean isDailishang =false;
        for(Role role:roles){
            if (role.getId()==4){
                isDailishang=true;
                break;
            }
        }
        List<Customer> customers = customerService.getCustomerList(customer,sysUser,isDailishang,pageRequest);
        int count = customerService.countCustomer(customer,sysUser,isDailishang);
        Map map = new HashMap();
        map.put("customers",customers);
        map.put("total",count);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/customer/customerInfo",method = RequestMethod.GET)
    public String customerInfo(@Valid int id){
        ResJson resJson = new ResJson();
        Customer customer = customerService.getCustomerInfoById(id);
        resJson.setData(customer);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/customer/updateCustomer",method = RequestMethod.POST)
    public String updateCustomer(@Valid Customer customer,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(customer.getId()==null||customer.getId()==0){
            resJson.setDesc("缺少主键");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            customer.setPicId(picId);
        }
        customer.setUpdaterId(sysUser.getId());
        try {
            customerService.updateCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            resJson.setDesc("客户信息修改失败！！");
            resJson.setStatus(0);
        }
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/customer/createCustomer",method = RequestMethod.POST)
    public String createCustomer(@Valid Customer customer,@Valid MultipartFile file){
        ResJson resJson = new ResJson();
        if(customer.getCustomerName()==null||"".equals(customer.getCustomerName())){
            resJson.setStatus(0);
            resJson.setDesc("请填入客户名称");
            return JSONObject.toJSONString(resJson);
        }else{
            customer.setCreaterId(sysUser.getId());
            customer.setStatus("1");
        }
        int picId = imageService.insertPictureFile(file,pictureVideoService,sysUser);
        if(picId==0){
            log.error("图片上传失败");
            resJson.setStatus(0);
            resJson.setDesc("图片上传失败");
            return JSONObject.toJSONString(resJson);
        }
        if(picId>0){
            customer.setPicId(picId);
        }
        customerService.createCustomer(customer);
        return JSONObject.toJSONString(resJson);
    }

}
