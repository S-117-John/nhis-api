package com.zebone;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMethodCache(basePackages = "com.zebone.modules")
@EnableCreateCacheAnnotation
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
