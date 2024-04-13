package com.thaprobit.resengine.controller;
import com.thaprobit.resengine.dao.User;
import com.thaprobit.resengine.dto.UserDto;
import com.thaprobit.resengine.repo.UserRepository;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDto userDto = new UserDto(user);
                    userDto.setReservations(user.getReservations()); // Set reservations
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setMiddleName(user.getMiddleName());
        existingUser.setLastName(user.getLastName());
        existingUser.setBirthday(user.getBirthday());

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
}
}
