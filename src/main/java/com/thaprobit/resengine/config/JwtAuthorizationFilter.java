package com.thaprobit.resengine.config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.thaprobit.resengine.config.JWTUtil.AUTH_HEADER;
import static com.thaprobit.resengine.config.JWTUtil.SECRET;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private UserAuthenticationProvider provider;

    public JwtAuthorizationFilter(UserAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/refresh-token")) {
            filterChain.doFilter(request, response);
        } else {
            String accessToken = provider.extractTokenFromHeaderIfExists(request.getHeader(AUTH_HEADER));
            if (accessToken != null) {
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(accessToken);
                String email = decodedJWT.getSubject();
                String role = null;
                try {
                    String[] roles = decodedJWT.getClaim("role").asArray(String.class);
                    if (roles != null && roles.length > 0) {
                        role = roles[0];
                    }
                } catch (NullPointerException e) {
                    System.out.println("JWT is missing the 'role' claim");
                }

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                if (role != null) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
