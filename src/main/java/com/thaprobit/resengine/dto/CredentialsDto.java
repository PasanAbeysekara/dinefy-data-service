package com.thaprobit.resengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialsDto {
    private String username;
    private String password;
}
