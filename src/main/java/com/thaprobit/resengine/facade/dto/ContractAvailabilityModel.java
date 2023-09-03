package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.AvailabilityID;
import lombok.*;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 10:41 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ContractAvailabilityModel {
    private AvailabilityID availabilityID;
    private Short count;
}
