package com.thaprobit.resengine.facade.dto;

import lombok.*;

/**
 * @author Tharindu Aththanayake
 * @since 01/02/2021 12:04 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LocationStateWrapper {
    private Short stateId;
    private String name;
}
