package com.skbaby.orange.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

    @Value("${orange.security.social.weixin.app-id}")
    private String appId;

    @Value("orange.security.social.weixin.app-secret")
    private String appSecret;
}
