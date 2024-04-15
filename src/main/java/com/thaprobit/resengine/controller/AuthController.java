package com.thaprobit.resengine.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thaprobit.resengine.config.UserAuthenticationProvider;
import com.thaprobit.resengine.controller.service.UserService;
import com.thaprobit.resengine.dto.CredentialsDto;
import com.thaprobit.resengine.dto.UserDto;
import com.thaprobit.resengine.dto.RegUserDto;
import com.thaprobit.util.URLProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.thaprobit.resengine.config.JWTUtil.AUTH_HEADER;
import static com.thaprobit.resengine.config.JWTUtil.SECRET;

@CrossOrigin(origins = "${client.url}")
@RequiredArgsConstructor
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class AuthController {
    private final UserService userService;
    private final UserAuthenticationProvider provider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto, HttpServletRequest request) {
        UserDto userDto = userService.login(credentialsDto);

        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        else {
            userDto.setAccessToken(provider.generateAccessToken(userDto));
            userDto.setRefreshToken(provider.generateRefreshToken(userDto));
            return ResponseEntity.ok(userDto);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegUserDto regUserDto, HttpServletRequest request) {
        try {
            if(userService.register(regUserDto))
            {
                return ResponseEntity.ok("User registered successfully!");
            }
            else {
                return ResponseEntity.badRequest().body("Failed to add user to database");
            }

        } catch (RuntimeException e) {
            if (e.getMessage().contains("already exists")) {
                return ResponseEntity.badRequest().body("Username already exists");
            } else {
                return ResponseEntity.internalServerError().body("Registration failed");
            }
        }
    }

    @GetMapping("/refresh-token")
    public void generateNewAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jwtRefreshToken = provider.extractTokenFromHeaderIfExists(request.getHeader(AUTH_HEADER));
        if (jwtRefreshToken != null) {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);
            String email = decodedJWT.getSubject();
            UserDto userDto = userService.findByUsername(email);
            String jwtAccessToken = provider.generateAccessToken(userDto);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), provider.getTokensMap(jwtAccessToken, jwtRefreshToken));
        } else {
            throw new RuntimeException("Refresh token required");
        }
    }
    @GetMapping("/decode")
    public ResponseEntity<?> decodeToken(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);

            if(session != null)
            {
                System.out.println("Session created with ID2: {}"+ session.getId());
                UserDto userDto = provider.decodeToken((String)session.getAttribute("token"));
                return ResponseEntity.ok(userDto);
            }
            else {
                System.out.println("Session null");

                return ResponseEntity.ok(null);
            }

        } catch (JWTDecodeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
        }
    }

}
