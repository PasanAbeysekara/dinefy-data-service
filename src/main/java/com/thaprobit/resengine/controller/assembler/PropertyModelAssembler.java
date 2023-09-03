package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.PropertyController;
import com.thaprobit.resengine.controller.converters.*;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.Property;
import com.thaprobit.resengine.facade.dto.MenuAndChoicesWrapper;
import com.thaprobit.resengine.facade.dto.PropertyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Tharindu Aththanayake
 * @since 09/14/2020 05:30 PM
 */
@Component
public class PropertyModelAssembler extends RepresentationModelAssemblerSupport<Property, PropertyModel> {

    @Autowired
    private ContractModelAssembler contractModelAssembler;

    @Autowired
    private LocationBasedModelConverter locationBasedModelConverter;

    @Autowired
    private PropFacilityModelConverter propFacilityModelConverter;

    @Autowired
    private PropTagsModelConverter propTagsModelConverter;

    @Autowired
    private ContactDetailsModelConverter contactDetailsModelConverter;

    @Autowired
    private OrganizationModelConverter organizationModelConverter;

    @Autowired
    private MenuAndChoicesWrapperModelConverter menuAndChoicesWrapperModelConverter;

    @Autowired
    private PropEventModelConverter propEventModelConverter;

    @Autowired
    private ReservationModelConverter reservationModelConverter;

    public PropertyModelAssembler() {
        super(PropertyController.class, PropertyModel.class);
    }

    @Override
    public PropertyModel toModel(Property entity) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setPropId(entity.getPropId());
        propertyModel.setCode(entity.getCode());
        propertyModel.setName(entity.getName());
        propertyModel.setDescription(entity.getDescription());
        propertyModel.setGeoLocation(null);
        propertyModel.setLatitude(entity.getLatitude());
        propertyModel.setLongitude(entity.getLongitude());
        propertyModel.setCurrentContId(entity.getCurrentContId());
        propertyModel.setStartTime(entity.getStartTime());
        propertyModel.setEndTime(entity.getEndTime());
        propertyModel.setAvgRating(entity.getAvgRating());
        propertyModel.setTotalRating(entity.getTotalRating());
        propertyModel.setAmount(entity.getAmount());
        propertyModel.setAmountCurrency(entity.getAmountCurrency());
        propertyModel.setAmountCondition(entity.getAmountCondition());
        propertyModel.setReservationSlotMinutes(entity.getReservationSlotMinutes());
        propertyModel.setReservationSlotLength(entity.getReservationSlotLength());
        propertyModel.setBookingNote(entity.getBookingNote());
        propertyModel.setOperationHours(entity.getOperationHours());
        propertyModel.setAvailabilityUnits(entity.getAvailabilityUnits());
        propertyModel.setBasedLocation(locationBasedModelConverter.convert(entity.getBasedLocation()));
        propertyModel.setCurrentContract(contractModelAssembler.toModel(entity.getCurrentContract()));
        propertyModel.setFacilities(entity.getFacilities().stream().map(propFacilityModelConverter::convert).collect(Collectors.toSet()));
        propertyModel.setTags(entity.getTags().stream().map(propTagsModelConverter::convert).collect(Collectors.toSet()));
        propertyModel.setContactDetails(contactDetailsModelConverter.convert(entity.getContactDetails()));
        propertyModel.setPropertySpecialities(entity.getPropertySpecialities());
        propertyModel.setPaymentOptions(entity.getPaymentOptions());
        propertyModel.setLivePromotions(entity.getLivePromotions());
        propertyModel.setOrganizations(organizationModelConverter.convert(entity.getOrganizations()));
        propertyModel.setTimeSlots(entity.getTimeSlots());
        propertyModel.setPropMenus(menuAndChoicesWrapperModelConverter.convert(new MenuAndChoicesWrapper(entity.getMenus(), entity.getChoices())));
        propertyModel.setPropertyMediaWrapper(entity.getPropertyMedia());
        propertyModel.setEvents(entity.getEvents().stream().map(propEventModelConverter::convert).collect(Collectors.toSet()));
        propertyModel.setReservations(entity.getReservations().stream().map(reservationModelConverter::convert).collect(Collectors.toSet()));

        propertyModel.add(HATEOASProvider.propertySelfLinkProvider(entity.getPropId()));

        return propertyModel;

    }
}

