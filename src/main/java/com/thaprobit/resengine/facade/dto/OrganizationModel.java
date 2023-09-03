package com.thaprobit.resengine.facade.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Tharindu Aththanayake
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationModel extends RepresentationModel<OrganizationModel> {
    private long orgId;
    private String code;
    private String name;
}
