package com.example.method.jwt;

import java.util.Date;
import com.example.method.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
public class TokenService
{
    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisService redisService;

    // 创建 token
    public  String createToken(String username) {
        // 使用 Jwts.builder() 方法创建 JWT 的 Builder
        return Jwts.builder()
                // 设置 token 的主题为用户名
                .setSubject(username)
                // 设置 token 的过期时间为当前时间加上设定的过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                // 使用 HS512 算法和密钥对 token 进行签名
                .signWith(SignatureAlgorithm.HS512, secret)
                // 将构建好的 token 转换为紧凑的形式并返回
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    // 刷新 token
    public  String refreshToken(String token) {
        Claims claims = parseToken(token);
        claims.setExpiration(new Date(System.currentTimeMillis() + expireTime));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
