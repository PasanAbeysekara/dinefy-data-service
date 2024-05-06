package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.PromoID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class PromotionModel extends RepresentationModel<PromotionModel> {
    private PromoID promoId;
    private int propId;
    private String description;
    private Date endDate;
    private Boolean live;
    private Boolean maxedOutLanding;
    private Boolean maxedOutSearch;
    private String name;
    private int promoType;
    private Date startDate;
    private int tierId;
}
