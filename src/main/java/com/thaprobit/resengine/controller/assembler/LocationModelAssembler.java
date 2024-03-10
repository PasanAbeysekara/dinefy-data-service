package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.LocationController;
import com.thaprobit.resengine.controller.converters.LocationStateModelConverter;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.LocationBased;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.LocationBasedModel;
import com.thaprobit.resengine.facade.dto.TagsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class LocationModelAssembler extends RepresentationModelAssemblerSupport<LocationBased, LocationBasedModel>
{
    public LocationModelAssembler() {
        super(LocationController.class, LocationBasedModel.class);
    }

    @Autowired
    private LocationStateModelConverter locationStateModelConverter;

    @Override
    public LocationBasedModel toModel(LocationBased entity) {
        LocationBasedModel locationBasedModel = new LocationBasedModel();
        locationBasedModel.setLocationId(entity.getLocationId());
        locationBasedModel.setName(entity.getName());
        locationBasedModel.setState(locationStateModelConverter.convert(entity.getState()));


        locationBasedModel.add(HATEOASProvider.sysFacilitySelfLinkProvider(Math.toIntExact(locationBasedModel.getLocationId())));

        return locationBasedModel;
    }

    @Override
    public CollectionModel<LocationBasedModel> toCollectionModel(Iterable<? extends LocationBased> entities) {
        CollectionModel<LocationBasedModel> facilitiesModel = super.toCollectionModel(entities);

        facilitiesModel.add(HATEOASProvider.sysFacilitySelfLinkProvider(10)); // TODO change

        return facilitiesModel;
    }
}
