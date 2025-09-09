package io.renren.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: wcy
 * @CreateTime: 2025-03-26
 * @Description:
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroFilterProperties {
    /**
     * 过滤器链配置
     * key: URL路径模式
     * value: 过滤器名称（anon/jwt等）
     */
    private Map<String, String> filterChain = new LinkedHashMap<>();


}
