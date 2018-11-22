package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.DataPic;
import com.hz.domain.Home;
import com.hz.service.CompanyResourceService;
import com.hz.service.PictureVideoService;
import com.hz.service.impl.ImageService;
import com.hz.util.ResJson;
import com.hz.util.page.PageRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyp
 * @date 2018/11/21
 */
@RestController
public class HzCompanyResourceController extends BaseController {


    @Autowired
    CompanyResourceService companyResourceService;

    @Autowired
    ImageService imageService;

    @Autowired
    PictureVideoService pictureVideoService;

    @RequestMapping(value = "/api/companyResource/homeList",method = RequestMethod.GET)
    public String homeList(@Valid Home home, @Valid PageRequest pageRequest){
        ResJson resJson = new ResJson();
        List<Home> homeList =companyResourceService.selectHomeList(home,pageRequest);
        int count = companyResourceService.countHomeList(home);
        Map map = new HashMap();
        map.put("homeList",homeList);
        map.put("total",count);
        resJson.setData(map);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/companyResource/createHome",method = RequestMethod.POST)
    public String createHome(@Valid Home home, @RequestParam("files") MultipartFile[] files){
        ResJson resJson = new ResJson();
        //插入图片
        List<Integer> list = imageService.insertPictureFiles(files,pictureVideoService,sysUser);

        int id = companyResourceService.createHome(home,list,sysUser);
        resJson.setData(id);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/companyResource/updateHome",method = RequestMethod.POST)
    public String updateHome(@Valid Home home ,@RequestParam("files") MultipartFile[] files){
        ResJson resJson = new ResJson();
        List<Integer> list = imageService.insertPictureFiles(files,pictureVideoService,sysUser);
        companyResourceService.updateHome(home,list,sysUser);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/companyResource/delPicData",method = RequestMethod.POST)
    public String delPicData(@Valid DataPic dataPic){
        ResJson resJson = new ResJson();
        if(dataPic.getModuleId()==0||dataPic.getModuleId()==null||dataPic.getPicId()==0||dataPic.getPicId()==null||dataPic.getDataId()==0||dataPic.getDataId()==null){
            resJson.setDesc("缺少参数，删除失败");
            resJson.setStatus(0);
        }else{
            companyResourceService.deleteDataPic(dataPic);
        }
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/companyResource/homeInfo",method = RequestMethod.GET)
    public String homeInfo(@Valid int id){
        ResJson resJson = new ResJson();
        Home home = companyResourceService.getHomeInfo(id);
        resJson.setData(home);
        return JSONObject.toJSONString(resJson);
    }



}
