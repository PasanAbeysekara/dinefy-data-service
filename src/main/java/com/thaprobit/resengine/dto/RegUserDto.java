package com.thaprobit.resengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
