package com.hr.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class  JWTUtil {
    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    @Autowired
    private JWTConfigProperties jwtConfigProperties;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 签名算法

    public  SecretKey generalKey() { // 获取加密KEY
        byte[] encodedKey = Base64.decodeBase64(Base64.encodeBase64(this.jwtConfigProperties.getSecret().getBytes()));
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
        return key;
    }
    /**
     * 生成token签名
     * @param subject
     * @return
     */
    public String createToken(String subject) {
        System.out.println(jwtConfigProperties.getExpire()+"  "+jwtConfigProperties.getSecret());
        Date now = new Date();
        // 过期时间
        Date expireDate = new Date(now.getTime() + jwtConfigProperties.getExpire() * 1000);

        //header参数
        final Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        //生成token
        String token = Jwts.builder()
                .setHeader(headerMap)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .setIssuer(jwtConfigProperties.getIssuer())
                .signWith(this.generalKey(),SignatureAlgorithm.HS256)
                .compact();

        logger.info("JWT[" + token + "]");
        return token;
    }

    /**
     * 解析token
     *
     * @param token token
     * @return
     */
    public Claims parseToken(String token) {

        Claims claims = null;
        try {
            //创建Signature SecretKey
            //final SecretKey key = Keys.hmacShaKeyFor(jwtConfigProperties.getSecret().getBytes(StandardCharsets.UTF_8));

            claims = Jwts.parserBuilder()
                    .setSigningKey(this.generalKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            logger.info("Parse JWT token success");
        } catch (JwtException e) {
            logger.info("Parse JWT errror " + e.getMessage());
            return null;
        }
        return claims;
    }
    /**
     * 根据token获取id
     * @param token
     * @return
     */
    public String getMemberIdByJwtToken(String token) {
        if (StringUtils.isEmpty(token)) return "";
        try {
            // 这里解析可能会抛异常，所以try catch来捕捉
           // Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(token);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(this.generalKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return (String) claims.get("sub");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

        public boolean checkToken (HttpServletRequest request){
            try {
                String jwtToken = request.getHeader("UserToken");
                if (StringUtils.isEmpty(jwtToken)) return false;
                Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(jwtToken);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
}
