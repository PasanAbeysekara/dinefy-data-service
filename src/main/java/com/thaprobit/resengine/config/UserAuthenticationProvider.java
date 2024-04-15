package com.thaprobit.resengine.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.thaprobit.resengine.controller.service.UserService;
import com.thaprobit.resengine.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.thaprobit.resengine.config.JWTUtil.*;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {


    private final UserService userService;

    public String generateAccessToken(UserDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + EXPIRE_ACCESS_TOKEN);

        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstName", user.getFirstName())
                .withClaim("lastName", user.getLastName())
                .withClaim("role", user.getRole())
                .sign(algorithm);
    }

    public String generateRefreshToken(UserDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + EXPIRE_REFRESH_TOKEN);

        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public String extractTokenFromHeaderIfExists(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public Map<String, String> getTokensMap(String jwtAccessToken, String jwtRefreshToken) {
        Map<String, String> idTokens = new HashMap<>();
        idTokens.put("accessToken", jwtAccessToken);
        idTokens.put("refreshToken", jwtRefreshToken);
        return idTokens;
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .username(decoded.getSubject())
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = userService.findByUsername(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public UserDto decodeToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .username(decoded.getSubject())
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .build();

        return user;
    }

}


