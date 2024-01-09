package edu.dufe.student.registry.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import edu.dufe.student.registry.constants.ErrorDictionary;
import edu.dufe.student.registry.data.in.UserData;
import edu.dufe.student.registry.exception.RestfulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "iat";
    private static final String CLAIM_HARD_EXPIRE = "hard_exp";
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.idle.expiration}")
    private Long idleExpiration;
    
    @Value("${jwt.hard.expiration}")
    private Long hardExpiration;
 
    private String generateToken(Map<String, Object> claims,boolean isInvalid) {
        Date expirationDate = null;
        if(isInvalid) {
        	expirationDate = new Date(System.currentTimeMillis());
        }else {
        	expirationDate = new Date(System.currentTimeMillis() + idleExpiration * 1000);
        }
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }
 
//    public void setExpire(String key, String val, long time) {
//        redisUtil.setExpire(key, val, time);
//    }
// 
//    public void del(String key) {
//        redisUtil.del(key);
//    }
// 
//    public Boolean validateToken(String authToken) {
//        Object o = redisUtil.get(authToken);
//        if(null != o){
//            return true;
//        }
//        return false;
//    }
 
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
			throw new RestfulException(ErrorDictionary.TOKEN_INVALID);
        }
        return claims;
    }
 
    public String generateToken(UserData userData) {
        Map<String, Object> claims = new HashMap<String, Object>(2);
        claims.put(CLAIM_KEY_USERNAME, userData.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        Date expirationDate = new Date(System.currentTimeMillis() + hardExpiration * 1000);
        claims.put(CLAIM_HARD_EXPIRE, expirationDate);
        return generateToken(claims,false);
    }
 
    public String getUsernameFromToken(String token) {
    	if(StringUtils.isEmpty(token)) {
			throw new RestfulException(ErrorDictionary.TOKEN_INVALID);
    	}
    	
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
			throw new RestfulException(ErrorDictionary.SESSION_TIMEOUT);
        }
        return username;
    }
 
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
			throw new RestfulException(ErrorDictionary.SESSION_TIMEOUT);
        }
    }
 
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims,false);
        } catch (Exception e) {
			throw new RestfulException(ErrorDictionary.SESSION_TIMEOUT);
        }
        return refreshedToken;
    }
    
    public String invalidToken(String token) {
        String invalidToken = "";
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            invalidToken = generateToken(claims,true);
        } catch (Exception e) {
			return null;
        }
        return invalidToken;
    }
 
    public Boolean validateToken(String token, String headerUserName) {
    	if(StringUtils.isEmpty(token)) {
			throw new RestfulException(ErrorDictionary.TOKEN_INVALID);
    	}
        String username = getUsernameFromToken(token);
        if(!username.equals(headerUserName)) {
			throw new RestfulException(ErrorDictionary.TOKEN_INVALID);
        }
        if(isTokenExpired(token)) {
			throw new RestfulException(ErrorDictionary.SESSION_TIMEOUT);
        }
        return true;
    }
}
