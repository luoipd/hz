package com.hz.util;

public class Constants {

	 
	public static String userStatus_1="1";//注册用户已审核

    public static Integer userStatus_3 = 3;
    public static Integer userStatus_4 = 4;
    public static Integer userStatus_5 = 5;//注册用户被锁定
    public static Integer userStatus_6 = 6;//注册用户未审核
    public static Integer userStatus_7 = 7;
    public static Integer userStatus_8 = 8;
	
	 
	
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
