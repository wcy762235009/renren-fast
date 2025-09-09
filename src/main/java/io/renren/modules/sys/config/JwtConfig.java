package io.renren.modules.sys.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wcy
 * @CreateTime: 2025-03-25
 * @Description:
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    private List<String> excludeUrls;
}
