package com.pearson.codingchallenge.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;


@ComponentScan({ "com.pearson.codingchallenge.*" })
@SpringBootApplication
@EnableCaching
public class Application
{
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		return cmfb;
	}
	
	@Bean
	public CacheManager cacheManager() {
		return ehCacheCacheManager().getObject();
	}
	
	/**
     * @param args.
     */
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}
