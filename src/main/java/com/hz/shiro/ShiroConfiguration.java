package com.hz.shiro;

import com.hz.shiro.shiroDomain.ShiroProperties;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class ShiroConfiguration {
	  @Autowired
	  public ShiroProperties properties;



	/**
	 *
	 * 注释掉该方法时 ，shiro的登录会话session由ehcache保持。
	 * 打开该方法时，shiro的登录回话session由redis保持。
	 * @param jedisShiroSessionRepository
	 * @return
	 */
	@Bean
	@DependsOn(value = { "jedisShiroSessionRepository" })
	public SessionDAO sessionDAO(JedisShiroSessionRepository jedisShiroSessionRepository) {
		final CustomShiroSessionDAO customShiroSessionDAO = new CustomShiroSessionDAO();
		customShiroSessionDAO.setShiroSessionRepository(jedisShiroSessionRepository);
		return customShiroSessionDAO;
	}
	
	 
	
	@Bean
	@DependsOn(value = { "objectRedisTemplate" })
	public JedisShiroSessionRepository jedisShiroSessionRepository(RedisTemplate<String, Object> objectRedisTemplate) {
		final JedisShiroSessionRepository jedisShiroSessionRepository = new JedisShiroSessionRepository();
		jedisShiroSessionRepository.setObjectRedisTemplate(objectRedisTemplate);
		return jedisShiroSessionRepository;
	}

	 
//	 /**
//     * (基于内存的)用户授权信息Cache
//     */
//    @Bean(name = "shiroCacheManager")
//    @ConditionalOnMissingBean(name = "shiroCacheManager")
//    @ConditionalOnMissingClass(value = {"org.apache.shiro.cache.ehcache.EhCacheManager"})
//    public CacheManager memoryCacheManager() {
//
//    	return new MemoryConstrainedCacheManager();
//    }
    

    /**
     * (基于redis的)用户授权信息Cache
     */
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean(name="shiroCacheManager")
    public CacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate) {

        return new RedisCacheManager(redisTemplate);
    }
    
//    /**
//     * (基于ehcache的)用户授权信息Cache
//     */
//    @Bean(name = "shiroCacheManager")
//    @ConditionalOnClass(name = {"org.apache.shiro.cache.ehcache.EhCacheManager"})
//    @ConditionalOnMissingBean(name = "shiroCacheManager")
//    public CacheManager ehcacheCacheManager() {
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ShiroProperties.Ehcache ehcache = properties.getEhcache();
//        if (ehcache.getCacheManagerConfigFile() != null) {
//            ehCacheManager.setCacheManagerConfigFile(ehcache.getCacheManagerConfigFile());
//        }
//        return ehCacheManager;
//    }


	@Bean(name = "lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@ConditionalOnMissingBean
	@Bean(name = "defaultAdvisorAutoProxyCreator")
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}


	@Bean(name = "securityManager")
	@DependsOn(value = {"shiroCacheManager", "rememberMeManager", "mainRealm"})
	public DefaultSecurityManager securityManager(Realm realm, RememberMeManager rememberMeManager,
												  CacheManager cacheManager, SessionManager sessionManager) {
		DefaultSecurityManager sm = new DefaultWebSecurityManager();
		sm.setCacheManager(cacheManager);
		sm.setSessionManager(sessionManager);
		sm.setRememberMeManager(rememberMeManager);
		sm.setRealm(realm);
		return sm;
	}
	/**
	 * cookie管理对象;
	 * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
		//System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
		return cookieRememberMeManager;
	}
	@Bean
	public SimpleCookie rememberMeCookie(){
		//System.out.println("ShiroConfiguration.rememberMeCookie()");
		//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//<!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	@Bean
	@ConditionalOnMissingBean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}






}