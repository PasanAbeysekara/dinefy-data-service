package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.OperationHours;
import com.thaprobit.resengine.dao.Promotion;
import com.thaprobit.resengine.dao.PropAvailabilityUnit;
import com.thaprobit.resengine.dao.PropMedia;
import com.thaprobit.resengine.dao.sys.PaymentOptions;
import com.thaprobit.resengine.dao.sys.PropertySpeciality;
import lombok.*;
import org.springframework.data.geo.Point;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

;


/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel extends RepresentationModel<PropertyModel> {
    private long propId;
    private String code;
    private String name;
    private String description;
    private Point geoLocation;
    private Double latitude;
    private Double longitude;
    private Integer currentContId;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal avgRating;
    private Integer totalRating;
    private BigDecimal amount;
    private String amountCurrency;
    private String amountCondition;
    private Short reservationSlotMinutes;
    private Short reservationSlotLength;
    private String bookingNote;
    private Set<OperationHours> operationHours;
    private Set<PropAvailabilityUnit> availabilityUnits;
    private LocationBasedModel basedLocation;
    private ContractModel currentContract;
    private Set<PropFacilityModel> facilities;
    private Set<PropTagsModel> tags;
    private ContactDetailsModel contactDetails;
    private Set<PropertySpeciality> propertySpecialities;
    private Set<PaymentOptions> paymentOptions;
    private Set<Promotion> livePromotions;
    private OrganizationModel organizations;
    private List<LocalTime> timeSlots;
    private MenuAndChoicesWrapperModel propMenus;
    private PropertyMediaWrapper propertyMediaWrapper;
    private Set<PropEventModel> events;
    private Set<ReservationModel> reservations;

    public void setPropertyMediaWrapper(Set<PropMedia> propMedia) {
        PropertyMediaWrapper propertyMediaWrapper = new PropertyMediaWrapper();
        propertyMediaWrapper.processPropertyMedia(propMedia);
        this.propertyMediaWrapper = propertyMediaWrapper;
    }


    /**
     * Add HATEOAS links for entities which are related to the property
     */
    public void linkPropertyEntities() {

        if (this.getAvailabilityUnits() != null) {
            for (PropAvailabilityUnit availabilityUnit : this.getAvailabilityUnits()) {
                if (availabilityUnit.getSysAvailabilityUnit() != null) {
                    Link selfRelSysAvailabilityUnit = HATEOASProvider.sysAvailabilityUnitSelfLinkProvider(availabilityUnit.getSysAvailabilityUnit().getUnitId());

                    availabilityUnit.getSysAvailabilityUnit().add(selfRelSysAvailabilityUnit);
                }
            }
        }

        if (this.getLivePromotions() != null) {
            this.getLivePromotions().forEach(promotion -> promotion.add(HATEOASProvider.promotionSelfLinkProvider(promotion.getPromoId())));
        }

    }

}
