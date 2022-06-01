package com.g7.ercsystem.rest.auth.jwt;

import com.g7.ercsystem.repository.UserRepository;
import com.g7.ercsystem.rest.auth.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    private  UserRepository userRepository;
    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;

    public JwtUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String generateTokenFromUsername(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime()+jwtExpirationMs)))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String generateJwtToken(UserDetailsImpl userPrincipal){
        return generateTokenFromUsername(userPrincipal.getId());
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserName(String headerAuth){
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            headerAuth = headerAuth.substring(7);
        }
        return getUserNameFromJwtToken(headerAuth);
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            log.error("Invalid JWT signature: {}",e.getMessage());
        }catch (MalformedJwtException e){
            log.error("Invalid JWT token: {}",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("JWT token is expired: {}",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("JWT token is unsupported: {}",e.getMessage());
        }catch(IllegalArgumentException e){
            log.error("JWT claims string is empty: {}",e.getMessage());
        }

        return false;
    }

}
