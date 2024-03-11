package com.thaprobit.resengine.controller.service;

import com.thaprobit.resengine.dao.User;
import com.thaprobit.resengine.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.thaprobit.resengine.dto.CredentialsDto;
import com.thaprobit.resengine.dto.UserDto;
import com.thaprobit.resengine.dto.RegUserDto;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    //private final BCryptPasswordEncoder passwordEncoder;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Unknown user"));

        if (!passwordMatches(credentialsDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        else {
            httpSession.setAttribute("logType", "normal");
        }

        return new UserDto(user);
    }

    private boolean passwordMatches(String enteredPassword, String storedPasswordHash) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(enteredPassword, storedPasswordHash);
    }


    public UserDto findByLogin(String login) {
        User user = userRepository.findByUsername(login)
                .orElseThrow(() -> new RuntimeException("Unknown user"));
        return new UserDto(user);
    }

    public void register(RegUserDto regUserDto) {
        if (userRepository.existsByUsername(regUserDto.getEmail())) {
            throw new RuntimeException("Username already exists");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(regUserDto.getPassword());

        //Long maxUserId = userRepository.findMaxUserId().orElse(0L);

        User newUser = new User();
       // newUser.setUserId(maxUserId +1);
        newUser.setFirstName(regUserDto.getFirstName());
        newUser.setLastName(regUserDto.getLastName());
        newUser.setUsername(regUserDto.getEmail());
        newUser.setPassword(encodedPassword);

        User savedUser = userRepository.save(newUser);

        if (savedUser == null) {
            throw new RuntimeException("Failed to add user to database");
        }
    }
}
