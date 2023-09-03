package com.thaprobit.resengine.facade.dto;

import lombok.*;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:30 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LocationBasedWrapper {
    private Long locationId;
    private String name;
    private Short stateId;
}
