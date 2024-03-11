package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.config.UserAuthenticationProvider;
import com.thaprobit.resengine.controller.service.UserService;
import com.thaprobit.resengine.dto.CredentialsDto;
import com.thaprobit.resengine.dto.UserDto;
import com.thaprobit.resengine.dto.RegUserDto;
import com.thaprobit.util.URLProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${client.url}")
@RequiredArgsConstructor
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class AuthController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);

        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        else {
            userDto.setToken(userAuthenticationProvider.createToken(userDto));
            return ResponseEntity.ok(userDto);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegUserDto regUserDto) {
        userService.register(regUserDto);
        return ResponseEntity.ok("User registered successfully!");
    }

}
