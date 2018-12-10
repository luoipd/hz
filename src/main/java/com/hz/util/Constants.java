package com.hz.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

	 
	public static String userStatus_1="1";//注册用户已审核

    public static Integer userStatus_3 = 3;
    public static Integer userStatus_4 = 4;
    public static Integer userStatus_5 = 5;//注册用户被锁定
    public static Integer userStatus_6 = 6;//注册用户未审核
    public static Integer userStatus_7 = 7;
    public static Integer userStatus_8 = 8;
	public static Integer userStatus_9 = 9;

	public  static final Map map ;
	static {
		Map aMap = new HashMap();
		aMap.put(1001, "hz_onefgdfghgf");
		aMap.put(1002, "hz_twojyfhvc");
		aMap.put(1003, "hz_threeiuhgj");
		aMap.put(1004, "hz_fourghjtty");
		aMap.put(1005, "hz_fourghjttyds");
		aMap.put(1006, "hz_fourghjttas");
		map = Collections.unmodifiableMap(aMap);
	}

	
	 
	
	public static String initPassword="111111";// 管理员在后台添加用户初始密码 111111 
	 
	
	
	/*****************************************shiro redis 管理设置 start*********************************************************/
	/**
	 * redis cache 前缀
	 */
	public final static String REDIS_SHIRO_CACHE = "shiro-cache:";

	/**
	 * redis session 前缀
	 */
	public final static String REDIS_SHIRO_SESSION = "shiro-session:";
	/*****************************************shiro redis 管理设置 end*********************************************************/
	 
	
	
	
	
}
