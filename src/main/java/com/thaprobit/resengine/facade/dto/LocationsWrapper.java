package com.thaprobit.resengine.facade.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:25 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LocationsWrapper {
    private Long countryId;
    private Set<LocationStateWrapper> states;
    private Page<LocationBasedWrapper> cities;
}
