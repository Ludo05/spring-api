package com.example.security.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final SecretKey jwtSecretKey;

    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig,
                            SecretKey jwtSecretKey) {
        this.jwtConfig = jwtConfig;
        this.jwtSecretKey = jwtSecretKey;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getPrefix())) {
            filterChain.doFilter(request,response);
            return;
        }

        String token = authorizationHeader.replace(jwtConfig.getPrefix(), "");
        try {
            final Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();

            String username = claims.getSubject();
            var authorities = (List<Map<String, String>>) claims.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities

            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token can not be trusted %s",token));
        }
        //Allows you to pass it along to the next filter in list.
        filterChain.doFilter(request,response);

    }
}
