package com.thaprobit.resengine.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;

public class JWTUtil {

    public static final long EXPIRE_ACCESS_TOKEN = 60*60*1000;

    public static final long EXPIRE_REFRESH_TOKEN = 120*60*1000;

    public static final String BEARER_PREFIX= "Bearer ";

    public static final String ISSUER = "springBootApp";

    // this is to avoid having the raw secret key available in the JVM
    private static String secretKey;

    static {
        // Initialize secretKey from property or default value
        secretKey = System.getProperty("security.jwt.token.secret-key", "secret-key");
    }
    public static final String SECRET =Base64.getEncoder().encodeToString(secretKey.getBytes());

    public static final String AUTH_HEADER = "Authorization";
}
