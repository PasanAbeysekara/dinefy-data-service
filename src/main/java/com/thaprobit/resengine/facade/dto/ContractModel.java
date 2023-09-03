package com.thaprobit.resengine.facade.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;
import java.util.Set;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 10:35 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ContractModel extends RepresentationModel<ContractModel> {
    private Long contractId;
    private Short version;
    private Long propId;
    private String name;
    private Date validFrom;
    private Date validTo;
    private String versionTxt;
    private Short bookableHorizon;
    private Set<SeasonModel> seasons;
}
