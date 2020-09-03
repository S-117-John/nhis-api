package com.zebone.core.launch.constant;

public interface AppConstant {


    /**
     * 应用版本
     */
    String APPLICATION_VERSION = "1.0.0";

    /**
     * 开发环境
     */
    String DEV_CODE = "dev";
    /**
     * 生产环境
     */
    String PROD_CODE = "prod";
    /**
     * 测试环境
     */
    String TEST_CODE = "test";

    /**
     * 代码部署于 linux 上，工作默认为 mac 和 Windows
     */
    String OS_NAME_LINUX = "LINUX";

    /**
     * 应用名前缀
     */
    String APPLICATION_NAME_PREFIX = "nhis-";

    String BASE_PACKAGES = "com.zebone";
    String APPLICATION_MOBILE_DOCTOR = "nhis/mobile/doctor";
    String APPLICATION_MOBILE_PATIENT = "nhis/mobile/patient";
    String APPLICATION_MOBILE_ORD = "nhis/mobile/ord";
    String APPLICATION_MOBILE_ORD_SET = "nhis/mobile/ord/set";
    String APPLICATION_MOBILE_ORD_PD = "nhis/mobile/ord/pd";
    String APPLICATION_MOBILE_DRUG = "nhis/mobile/drug";
    String APPLICATION_MOBILE_BD_TERM = "nhis/mobile/bd/term";
    String APPLICATION_MOBILE_BD_SUPPLY = "nhis/mobile/bd/supply";
    String APPLICATION_MOBILE_BD_OU_DEPT = "nhis/mobile/bd/ou/dept";
}
