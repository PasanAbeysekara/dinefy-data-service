package com.thaprobit.resengine.controller;
import com.thaprobit.resengine.dao.User;
import com.thaprobit.resengine.repo.UserRepository;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all employees
    @GetMapping("/user")
    public List<User> getAllEmployees(){
        return userRepository.findAll();
    }

}
