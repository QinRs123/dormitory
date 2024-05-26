package com.atguigu.cloud.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 生成或者解析jwt令牌
 */
//@Data
@Component
public class JwtUntil {
    //static 静态变量使用@Value注解是无效的，详情自己上网查


    private static String signKey="itCaiNiao";
    private static Long expire=4320000L;

//    @Autowired
//    public static JwtPropreties jwtPropreties;   //失败

    /**
     * 生成jwt令牌
     */
    public static String CreateJwt(Map<String ,Object> claims){
        //获取配置类参数
//        String signKey1 = jwtPropreties.getSignKey();
//        Long expire1 = jwtPropreties.getExpire();

        String jwt= Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }


    public static Claims parseJWT(String jwt) {
//        String signKey1 = jwtPropreties.getSignKey();
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
