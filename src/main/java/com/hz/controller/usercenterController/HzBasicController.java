package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.Industry;
import com.hz.domain.Purpose;
import com.hz.domain.Tag;
import com.hz.service.BasicService;
import com.hz.service.CompanyResourceService;
import com.hz.util.ResJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lyp
 * @date 2018/11/19
 */
@RestController
public class HzBasicController extends BaseController {


    @Autowired
    BasicService basicService;
    @Autowired
    CompanyResourceService companyResourceService;

    @RequestMapping(value = "/api/hzBasic/getTagList",method = RequestMethod.GET)
    @ResponseBody
    public String getTagList(@Valid int tagType){
        ResJson resJson = new ResJson();
        List<Tag> tagList =  basicService.getTagList(tagType);
        resJson.setData(tagList);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzBasic/insertTag",method = RequestMethod.POST)
    @ResponseBody
    public String createTag(@Valid Tag tag){
        ResJson resJson = new ResJson();
        if(tag.getTagName()!=null&&tag.getTagName()!=""){
            tag.setCreaterId(sysUser.getId());
            tag.setStatus(1);
        }
        basicService.insertTag(tag);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzBasic/updateTag",method = RequestMethod.POST)
    @ResponseBody
    public String updateTag(@Valid Tag tag){
        ResJson resJson = new ResJson();
        basicService.updateTag(tag);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzBasic/delTag",method = RequestMethod.POST)
    @ResponseBody
    public String delTag(@Valid int id){
        ResJson resJson = new ResJson();
        basicService.deleteTag(id,sysUser);
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/api/hzBasic/industryList",method = RequestMethod.GET)
    public String getIndustryList(){
        ResJson resJson = new ResJson();
        List<Industry> industries = basicService.getIndustryList();
        resJson.setData(industries);
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/api/hzBasic/insertIndustry",method = RequestMethod.POST)
    public String insertIndustry(@Valid Industry industry){
        ResJson resJson = new ResJson();
        if(industry.getIndustryName()==null||"".equals(industry.getIndustryName())){
            resJson.setDesc("请输入名称！！！");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        industry.setCreaterId(sysUser.getId());
        industry.setStatus(1);
        basicService.insertIndustry(industry);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 更新&&物理删除
     * @param industry
     * @return
     */
    @RequestMapping(value = "/api/hzBasic/updateIndustry", method = RequestMethod.POST)
    public String updateIndustry(@Valid Industry industry){
        ResJson resJson = new ResJson();
        if(industry.getId()==null){
            resJson.setDesc("缺少id");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        industry.setUpdaterId(sysUser.getId());
        basicService.updateIndustry(industry);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 全删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/hzBasic/delIndustry",method = RequestMethod.POST)
    public String delIndustry(@Valid int id){
        ResJson resJson = new ResJson();
        basicService.deleteIndustryById(id);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzBasic/purposeList",method = RequestMethod.GET)
    public String getPurposeList(){
        ResJson resJson = new ResJson();
        List<Purpose> industries = basicService.getPurposeList();
        resJson.setData(industries);
        return JSONObject.toJSONString(resJson);
    }
    @RequestMapping(value = "/api/hzBasic/insertPurpose",method = RequestMethod.POST)
    public String insertPurpose(@Valid Purpose purpose){
        ResJson resJson = new ResJson();
        if(purpose.getPurposeName()==null||"".equals(purpose.getPurposeName())){
            resJson.setDesc("请输入名称！！！");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        purpose.setStatus(1);
        purpose.setCreaterId(sysUser.getId());
        basicService.insertPurpose(purpose);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 更新&&物理删除
     * @param purpose
     * @return
     */
    @RequestMapping(value = "/api/hzBasic/updatePurpose",method = RequestMethod.POST)
    public String updatePurpose(@Valid Purpose purpose){
        ResJson resJson = new ResJson();
        if(purpose.getId()==null){
            resJson.setDesc("缺少id");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        basicService.updatePurpose(purpose);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 全删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/hzBasic/delPurpose",method = RequestMethod.POST)
    public String delPurpose(@Valid int id){
        ResJson resJson = new ResJson();
        basicService.deletePurposeById(id);
        return JSONObject.toJSONString(resJson);
    }





}
