package com.hz.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author lyp
 * @date 2018/11/14
 */
@Configuration
public class ImageUtil {

    @Value("${img.url}")
    private String url;

    public static String UserFolder = "user";//用户图片存储路径
    public static String MediaFolder = "media";//媒体图片存储路径
    public static String ContactUsFolder = "contactUs";//联系我们
    public static String CustomerCaseFolder = "customerCase";
    public static String HuiBaoFolder = "huiBao";
    public static String AdvertisingStyleFolder = "advertisingStyle";
    public static String HomeFolder = "home";
    public static String MarketFolder = "market";
    public static String CustomerFolder = "customer";
    public static String ProposalFolder = "proposal";

    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public String saveImg(MultipartFile multipartFile, String path,String fileName) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

    public String getFileName(String folder,String filename){
        long currentTime = System.currentTimeMillis();
        int num  = (int) (Math.random()*100);
        String ff = StringUtils.substringAfterLast(filename,".");
        String FileName = folder+String.valueOf(currentTime)+String.valueOf(num)+"."+ff;

        return FileName;
    }

    public String getPicUrl(String folder,String filename){
        String url1 = this.url+"/"+folder+"/"+filename;
        return url1;
    }


}
