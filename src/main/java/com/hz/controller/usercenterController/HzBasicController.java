package com.hz.controller.usercenterController;

import com.alibaba.fastjson.JSONObject;
import com.hz.controller.BaseController;
import com.hz.domain.Industry;
import com.hz.domain.Purpose;
import com.hz.domain.Tag;
import com.hz.service.BasicService;
import com.hz.service.CompanyResourceService;
import com.hz.util.ImageUtil;
import com.hz.util.MultipartFileUtil;
import com.hz.util.ResJson;
import com.hz.util.WebAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.validation.Valid;
import java.io.*;
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
    @Autowired
    ImageUtil imageUtil;
    @Autowired
    WebAppConfig webAppConfig;

    @RequestMapping(value = "/api/hzBasic/getTagList",method = RequestMethod.GET)
    @ResponseBody
    public String getTagList(@Valid Tag tag){
        ResJson resJson = new ResJson();
        List<Tag> tagList =  basicService.getTagList(tag);
        resJson.setData(tagList);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzBasic/insertTag",method = RequestMethod.POST)
    @ResponseBody
    public String createTag(@Valid Tag tag){
        ResJson resJson = new ResJson();
        if(tag.getTagName()==null||tag.getTagName().equals("")){
            resJson.setDesc("标签名不能为空！");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        Tag tag1 = new Tag();
        tag1.setTagName(tag.getTagName().trim());
        tag1.setTagType(1);
        List<Tag> tagList = basicService.getTagListCheck(tag1);
        if(tagList.size()>0){
            resJson.setDesc("标签名已存在");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        tag.setCreaterId(sysUser.getId());
        tag.setStatus(1);
        basicService.insertTag(tag);
        return JSONObject.toJSONString(resJson);
    }

    @RequestMapping(value = "/api/hzBasic/updateTag",method = RequestMethod.POST)
    @ResponseBody
    public String updateTag(@Valid Tag tag){
        ResJson resJson = new ResJson();
        if(tag.getTagName()==null||tag.getTagName().equals("")){
            resJson.setDesc("标签名不能为空！");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        Tag tag1 = new Tag();
        tag1.setTagName(tag.getTagName().trim());
        tag1.setTagType(1);
        List<Tag> tagList = basicService.getTagListCheck(tag1);
        if(tagList.size()>0){
            resJson.setDesc("标签名已存在");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        tag.setUpdaterId(sysUser.getId());
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
    public String getIndustryList(@Valid Industry industry){
        ResJson resJson = new ResJson();
        List<Industry> industries = basicService.getIndustryList(industry);
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
        List<Industry> industries = basicService.getIndustryListCheck(industry.getIndustryName().trim());
        if(industries.size()>0){
            resJson.setDesc("行业已存在");
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
        List<Industry> industries = basicService.getIndustryListCheck(industry.getIndustryName().trim());
        if(industries.size()>0){
            resJson.setDesc("行业已存在");
            resJson.setStatus(0);
            return JSONObject.toJSONString(resJson);
        }
        industry.setUpdaterId(sysUser.getId());
        basicService.updateIndustry(industry);
        return JSONObject.toJSONString(resJson);
    }

    /**
     * 全删除
     * @param industry
     * @return
     */
    @RequestMapping(value = "/api/hzBasic/delIndustry",method = RequestMethod.POST)
    public String delIndustry(@Valid Industry industry){
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

    @RequestMapping(value = "/api/hzBasic/upload",method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") String file) throws UnsupportedEncodingException {
        ResJson resJson = new ResJson();
        request.setCharacterEncoding("UTF-8");
        MultipartFile file1 = MultipartFileUtil.base64ToMultipart(file);
        File file2 = null;
        try {
            file2 = imageUtil.saveFile(file1,webAppConfig.location + ImageUtil.ProposalCutFolder,"tet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("image/jpg");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + imageUtil.getFileName(ImageUtil.ProposalCutFolder
                ,file1.getOriginalFilename()));// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(webAppConfig.location + ImageUtil.ProposalCutFolder+"/tet.png");
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            return JSONObject.toJSONString(resJson);
        } catch (Exception e) {
            e.printStackTrace();
            return "下载失败";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    public static void main(String[] args) {
        try {
            //转码
            System.out.println(new sun.misc.BASE64Encoder().encode("666666".getBytes()));
            //解码
            BASE64Decoder decoder = new BASE64Decoder();
            System.out.println(new String(decoder.decodeBuffer("NjY2NjY2")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }







}
