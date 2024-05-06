package com.thaprobit.resengine.controller.service;

import com.thaprobit.resengine.dao.Property;
import com.thaprobit.resengine.dao.User;
import com.thaprobit.resengine.repo.PropertyRepository;
import com.thaprobit.resengine.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.thaprobit.resengine.dto.CredentialsDto;
import com.thaprobit.resengine.dto.UserDto;
import com.thaprobit.resengine.dto.RegUserDto;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service

public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final PropertyRepository propertyRepository;




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


    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Unknown user"));
        return new UserDto(user);
    }

    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Unknown user"));
        return user.getUserId();
    }

    public boolean register(RegUserDto regUserDto) {
        if (userRepository.existsByUsername(regUserDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(regUserDto.getPassword());

        //Long maxUserId = userRepository.findMaxUserId().orElse(0L);

        User newUser = new User();
       // newUser.setUserId(maxUserId +1);
        newUser.setFirstName(regUserDto.getFirstName());
        newUser.setLastName(regUserDto.getLastName());
        newUser.setUsername(regUserDto.getUsername());
        newUser.setRole("customer");
        newUser.setPassword(encodedPassword);


        User savedUser = userRepository.save(newUser);

        if (savedUser == null) {
            return false;
        }
        else {
            return true;
        }
    }
    public User updateUserDetails(Long userId, User userDetails) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        existingUser.setAddress1(userDetails.getAddress1());
        existingUser.setAddress2(userDetails.getAddress2());
        existingUser.setCity(userDetails.getCity());
        existingUser.setDistrict(userDetails.getDistrict());
        existingUser.setProvince(userDetails.getProvince());
        existingUser.setCountry(userDetails.getCountry());
//        Set<String> preferredRestaurants = new HashSet<>(userDetails.getpreferredProperty());
//        existingUser.setpreferredProperty(preferredProperty);
        Set<Property> PreferredProperties = new HashSet<>();
        for (Property prop : userDetails.getPreferredProperties()) {
            Property property = propertyRepository.findByName(prop.getName())
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " ));
            PreferredProperties.add(property);
        }
        existingUser.setPreferredProperties(PreferredProperties);

//        for (String propertyName : userDetails.getddPreferredProperty()) {
//            Property property = Property.findByName(Name)
//                    .orElseThrow(() -> new ResourceNotFoundException("Property not found with name: " + propertyName));
//            preferredProperties.add(property);
//        }
//        existingUser.setPreferredProperties(preferredProperties);
        return userRepository.save(existingUser);
    }


}
