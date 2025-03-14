package com.doganmehmet.app.jwt;

import com.doganmehmet.app.entity.RefreshToken;
import com.doganmehmet.app.entity.User;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JWTTransactions {
    private static final String SECRET_KEY = "d74368cf6ff18be99c74e351e8673c5bda52ac7c4396a301a0873d812e96010dc96c5d209ad559daa32177f5c360d4ab96a72f96511ca91bfc7a2757062a3b9d553245c7a741c041cc84a79a924ea9d9cb2384425e2e69ef1114360aec35143fbc594e59cabb3432ee650f7ed18ebdd101a894d1ba3a0b09825b467950f1d9d4";

    private Key getKey()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    private Claims getClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateJwtToken(UserDetails userDetails)
    {
        var roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public RefreshToken creatRefreshToken(User user)
    {
        var refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiresDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        return refreshToken;
    }

    public String getUsernameByToken(String token)
    {
        var claims = getClaims(token);
        return claims != null ? claims.getSubject() : null;
//        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token)
    {
        try {
            return getClaims(token).getExpiration().before(new Date());
        }
        catch (Exception ignored) {
            return true;
        }
    }

    public List<String> getRolesFromToken(String token)
    {
        return getClaims(token).get("roles", List.class);
    }

}
