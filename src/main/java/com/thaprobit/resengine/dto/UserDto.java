package com.thaprobit.resengine.dto;

import com.thaprobit.resengine.dao.Property;
import com.thaprobit.resengine.dao.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.thaprobit.resengine.dao.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

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
        //this.birthday = user.getBirthday();
        this.address1 = user.getAddress1();
        this.address2 = user.getAddress2();
        this.city = user.getCity();
        this.district = user.getDistrict();
        this.province = user.getProvince();
        this.country = user.getCountry();
//        this.preferredProperty = new HashSet<>();
////        for (Property property : user.getPreferredProperties()) {
////            this.preferredProperty.add(property.getName());
////        }
        this.PreferredProperties = user.getPreferredProperties().stream()
                                    .map(Property::getName)
                                    .collect(Collectors.toSet());

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
    //private Date birthday;
    private String address1;
    private String address2;
    private String city;
    private String district;
    private String province;
    private String country;
    private Set<String> PreferredProperties= new HashSet<>();

    private Set<Reservation> reservations;
    // Add reservations field
    private String role;
    private String accessToken;
    private String refreshToken;
}

