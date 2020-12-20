package com.chen.human_resource_system.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author CHEN
 * @date 2020/3/1 17:02
 * jwt 的工具类
 */
@Component
public class JWTUtil {


    // 过期时间3天
    private static final long EXPIRE_TIME = 3*60*60*1000*24;
    private static final Logger PLOG = LoggerFactory.getLogger(JWTUtil.class);


    /**
     * 校验token是否正确
     * @param token TOKEN
     * @param secret 用户的密码
     * @return boolean
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            PLOG.error("JWT >> " + e);
            return false;
        }
    }

    /**
     * 无需解密直接获得token中的用户名
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            PLOG.error("JWT >> " + e);
            return null;
        }
    }
    /**
     * 生成签名，并设置过期时间
     * @param username 用户名
     * @param secret 用户的密码
     * @return token
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);

            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            PLOG.error("JWT >> " + e);
            return null;
        }
    }


}
