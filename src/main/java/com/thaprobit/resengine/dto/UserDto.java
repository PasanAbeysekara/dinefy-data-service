package com.thaprobit.resengine.dto;

import com.thaprobit.resengine.dao.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.thaprobit.resengine.dao.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    public UserDto(User user) {
        //this.id = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public UserDto(RegUserDto user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        //this.token = null;
    }

    //private Long id;
    private String firstName;
    private String lastName;
    private String username;

    private Set<Reservation> reservations; // Add reservations field
    private String role;
    private String accessToken;
    private String refreshToken;
}

