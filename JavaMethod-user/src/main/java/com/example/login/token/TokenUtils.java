package com.example.login.token;

import com.example.user.entity.Users;
import io.jsonwebtoken.*;

import java.util.Date;

public class TokenUtils {
    //过期时间
    private static final long EXPIRE_TIME= 1000;//60000*60*24*7;
    //秘钥
    private static final String PRI_SECRET="daencode";
    //主体
    private static final String SUBJECT="daencode";

    //token生成
    public static String genToken(Users user){
        String token= Jwts.builder()
                .setSubject(SUBJECT)//主题
                .claim("username",user.getUsername())//用户名
                .claim("id",user.getUserId())//用户名、id，还可以放其他多个信息。不要放敏感信息
                .setIssuedAt(new Date())//颁发日期
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256,PRI_SECRET)
                .compact();
        return token;
    }
    //token验证
    public static Claims verifyToken(String token) throws Exception {
        //从token中解析出claims
        Claims claims=Jwts.parser().setSigningKey(PRI_SECRET).parseClaimsJws(token).getBody();
        return claims;
        /*try {
            Claims claims=Jwts.parser().setSigningKey(PRI_SECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }*/
    }
}
