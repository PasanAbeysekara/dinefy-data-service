package com.thaprobit.resengine.config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
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
                long count = accessToken.chars().filter(ch -> ch == '.').count();
                System.out.println("count-"+count);
                if(count == 2)
                {
                    System.out.println("jwt token");
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
                }
                else {
                    System.out.println("google token "+accessToken);

                    // Set up HTTP headers with the access token
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Authorization", "Bearer " + accessToken);

                    // Create an HTTP entity with headers
                    HttpEntity<String> entity = new HttpEntity<>(headers);

                    // Make a GET request to the UserInfo endpoint
                    ResponseEntity<String> res = new RestTemplate()
                            .exchange("https://www.googleapis.com/oauth2/v3/userinfo", HttpMethod.GET, entity, String.class);

                    // Print the response body
                    System.out.println(res.getBody());

                    // Parse the JSON response
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(res.getBody());

                    // Extract and print the email field
                    String email = jsonNode.get("email").asText();
                    System.out.println("Email: " + email);

                    Collection<GrantedAuthority> authorities = new ArrayList<>();

                    authorities.add(new SimpleGrantedAuthority("customer"));


                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(token);
                    filterChain.doFilter(request, response);
                }

            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

}

