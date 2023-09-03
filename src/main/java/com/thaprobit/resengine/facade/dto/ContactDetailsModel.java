package com.thaprobit.resengine.facade.dto;

import lombok.*;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:54 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ContactDetailsModel {
    private Long contactId;
    private String type;
    private String name;
    private String email;
    private String web;
    private String phonePrimary;
    private String phoneSecondary;
    private String address1;
    private String address2;
    private String address3;
    private String zip;
}
