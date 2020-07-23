package com.zebone.core.log.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

/**
 * 日志工具自动配置
 */
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogToolAutoConfiguration {
}
