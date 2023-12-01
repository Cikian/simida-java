package cn.simida.utils;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/19 14:22
 */

import cn.simida.common.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {

    // 有效期
    public static final Long JWT_TTL = 5 * 60 * 60 * 1000L; // 2小时

    // JWT令牌信息
    public static final String JWT_KEY = "www.cikian.cn";

    public static String createJWT(User user, String subject, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }

        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(user.getUserId())        // 设置唯一编号
                .setSubject(subject)            // 设置主题 可以是JSON数据
                .claim("user", user)            // 自定义claims
                .setIssuer("admin")
                .setIssuedAt(now)               // 设置签发日期
                .setExpiration(expDate)         // 设置过期时间
                .setAudience(user.getUserName())// 设置接收方
                .claim("role", user.getRole())         // 设置角色
                // 设置签名 使用HS256算法 并设置SecretKey(字符串)
                .signWith(SignatureAlgorithm.HS256, secretKey);
        return builder.compact();


    }

    /**
     * 生成加密secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JWT_KEY.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析令牌数据
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static User getUserByToken(String token) throws Exception {
        Claims claims = parseJWT(token);
        Map<String, Object> userMap = (Map<String, Object>) claims.get("user");
        return new ObjectMapper().convertValue(userMap, User.class);
    }


}

