package com.thaprobit.resengine.controller.service;

import com.thaprobit.resengine.dao.User;
import com.thaprobit.resengine.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.thaprobit.resengine.dto.CredentialsDto;
import com.thaprobit.resengine.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Unknown user"));

        if (!passwordMatches(credentialsDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new UserDto(user);
    }

    private boolean passwordMatches(String enteredPassword, String storedPasswordHash) {

        return Objects.equals(enteredPassword, storedPasswordHash);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByUsername(login)
                .orElseThrow(() -> new RuntimeException("Unknown user"));
        return new UserDto(user);
    }
}
