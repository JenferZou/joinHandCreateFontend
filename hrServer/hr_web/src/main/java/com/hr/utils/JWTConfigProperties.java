package com.hr.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "custom.jwt")
public class JWTConfigProperties {
    private String secret;
    // 有效时间
    private Long expire;
    // 用户凭证
    private String header;
    // 签发者
    private String issuer;


}
