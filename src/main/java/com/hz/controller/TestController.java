package com.hz.controller;

import com.hz.service.UserService;
import com.hz.shiro.UserPermission;
import com.hz.util.Constants;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermission userPermission;

//    @RequestMapping(value = "/testUser", method = RequestMethod.GET)
//    public User findUser() {
//        return userService.getUser(1);
//    }
//    @RequestMapping(value = "/index1", method = RequestMethod.GET)
//    public String index(ModelMap modelMap) {
//        User user = userService.getUser(1);
//        modelMap.addAttribute("user",user);
//
//        return "/login";
//    }
    @Autowired
StringEncryptor stringEncryptor;
    @RequestMapping(value = "/api/testUser", method = RequestMethod.GET)
    public String getTest(HttpServletRequest request){
        userService.getUser(1);
        userPermission.getPermission("1","dfsadf");
        String result = stringEncryptor.encrypt("admin123$%^");
        return result;
    }

    public static void main(String args[]){
        long timestamp = System.currentTimeMillis();
        String test = new Md5Hash("test", "cessd", 1024).toBase64();//098f6bcd4621d373cade4e832627b4f6
        String test1 = md5Password("test");
        String salt = (String) Constants.map.get(1001);
        System.out.println(test1);
        int i = 0;
        Integer i1 = i;
        System.out.println(salt);
        StringEncryptor stringEncryptor;

    }

    public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }





}
