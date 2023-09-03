package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FacilitiesModel extends RepresentationModel<FacilitiesModel> {
    private int facilityId;
    private String code;
    private String name;
    private String description;
    private String icon;
}
