package com.thaprobit.resengine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.thaprobit.resengine.dao.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    public UserDto(User user) {
        this.id = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.token = null;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String token;

}
