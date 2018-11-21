package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.Tag;
import com.hz.service.BasicService;
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
        basicService.deleteTag(id);
        return JSONObject.toJSONString(resJson);
    }


}
