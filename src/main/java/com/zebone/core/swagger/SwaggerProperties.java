package com.zebone.core.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private List<String> basePackages = new ArrayList(Collections.singletonList("com.zebone"));
    private List<String> basePath = new ArrayList();
    private List<String> excludePath = new ArrayList();
    private String title = "NHIS 接口文档系统";
    private String description = "NHIS 接口文档系统";
    private String version = "2.5.1.RELEASE";
    private String license = "Powered By NHIS";
    private String licenseUrl = "http://www.zebone.cn";
    private String termsOfServiceUrl = "http://www.zebone.cn";
    private String host = "";
    private SwaggerProperties.Contact contact = new SwaggerProperties.Contact();
    private SwaggerProperties.Authorization authorization = new SwaggerProperties.Authorization();






    @Data
    public static class AuthorizationScope {
        private String scope = "";
        private String description = "";


    }

    @Data
    public static class Authorization {
        private String name = "";
        private String authRegex = "^.*$";
        private List<SwaggerProperties.AuthorizationScope> authorizationScopeList = new ArrayList();
        private List<String> tokenUrlList = new ArrayList();


    }

    @Data
    public static class Contact {
        private String name = "lijin";
        private String url = "https://github.com/S-117-John";
        private String email = "lijin77555@gmail.com";


    }
}
