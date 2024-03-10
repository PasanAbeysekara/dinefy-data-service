package com.thaprobit.resengine.controller.service;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.assembler.LocationModelAssembler;
import com.thaprobit.resengine.controller.converters.LocationsWrapperConverter;
import com.thaprobit.resengine.dao.LocationBased;
import com.thaprobit.resengine.facade.dto.LocationBasedModel;
import com.thaprobit.resengine.facade.dto.LocationsWrapper;
import com.thaprobit.resengine.repo.LocationRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharindu Aththanayake
 * @since 12/28/2020 20:30 PM
 */
@Service
@Slf4j
public class LocationService extends AbstractService<LocationBased> {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationsWrapperConverter locationsWrapperConverter;


    @Autowired
    private PagedResourcesAssembler<LocationBased> pagedResourcesAssembler;

    @Autowired
    private LocationModelAssembler locationModelAssembler;


    /**
     * Get All based locations
     *
     * @param pageable The Pageable
     * @return All base locations
     */
//    public ResponseEntity<ResponseWrapper<LocationsWrapper>> getLocations(Pageable pageable) {
//        List<LocationBased> locationBasedList = locationRepository.findAll(pageable).getContent();
//
//        ResponseEntity<ResponseWrapper<LocationsWrapper>> response;
//
//        if (!locationBasedList.isEmpty()) {
//            LocationsWrapper locationsWrapper = locationsWrapperConverter.convert(locationBasedList);
//            response = ResponseEntity.ok()
//                    .headers(addCommonHeaders(new HttpHeaders()))
//                    .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, locationsWrapper));
//        } else {
//            response = ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .headers(new HttpHeaders())
//                    .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.NOT_FOUND, ""));
//        }
//
//        return response;
//    }

    public ResponseEntity<ResponseWrapper<PagedModel<LocationBasedModel>>> getLocations(Pageable pageable) {
        Page<LocationBased> locationBasedPaged = locationRepository.findAll(pageable);
        PagedModel<LocationBasedModel> collModel = pagedResourcesAssembler.toModel(locationBasedPaged, locationModelAssembler);

        return ResponseEntity.ok()
                .headers(addCommonHeaders(new HttpHeaders()))
                .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, collModel));
    }

    /**
     * Get Single Location
     *
     * @param locationId Based Location ID
     * @return The Based Location
     */
    public ResponseEntity<ResponseWrapper<LocationBased>> getBasedLocation(long locationId) {
        Optional<LocationBased> optionalLocationBased = locationRepository.findById(locationId);

        ResponseEntity<ResponseWrapper<LocationBased>> response;

        if (optionalLocationBased.isPresent()) {
            LocationBased locationBased = optionalLocationBased.get();

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, locationBased));
        } else {
            response = buildNotFoundResponseWrapped();
        }

        return response;
    }


    /**
     * Create a new base location
     *
     * @param locationBased LocationBased
     * @return Saved LocationBased
     */
    public ResponseEntity<ResponseWrapper<LocationBased>> createLocationBased(LocationBased locationBased) {
        ResponseEntity<ResponseWrapper<LocationBased>> response;

        try {
            LocationBased savedLocationBased = locationRepository.save(locationBased);

            response = ResponseEntity.status(HttpStatus.CREATED)
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.LOCATION_BASED_CREATE_SUCCESS, savedLocationBased));
        } catch (Exception e) {
            response = buildExceptionErrorResponse(SystemOperation.CREATE, SystemMessages.LOCATION_BASED_CREATE_FAILED, e);
        }

        return response;
    }

    /**
     * Update a based location
     *
     * @param locationId    LocationBased ID
     * @param locationBased LocationBased
     * @return Updated LocationBased
     */
    public ResponseEntity<ResponseWrapper<LocationBased>> updateLocationBased(long locationId, LocationBased locationBased) {
        ResponseEntity<ResponseWrapper<LocationBased>> response;

        try {
            locationBased.setLocationId(locationId);

            LocationBased updatedLocationBased = locationRepository.save(locationBased);

            response = ResponseEntity.status(HttpStatus.CREATED)
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.MODIFY.withSuccess(), SystemMessages.LOCATION_BASED_UPDATE_SUCCESS, updatedLocationBased));
        } catch (Exception e) {
            response = buildExceptionErrorResponse(SystemOperation.MODIFY, SystemMessages.LOCATION_BASED_UPDATE_FAILED, e);
        }

        return response;
    }

    /**
     * Delete LocationBased
     *
     * @param locationId LocationBased ID
     * @return Delete LocationBased
     */
    public ResponseEntity<ResponseWrapper<LocationBased>> deleteLocationBased(long locationId) {
        ResponseEntity<ResponseWrapper<LocationBased>> response;

        try {
            locationRepository.deleteById(locationId);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.DELETE.withSuccess(), SystemMessages.LOCATION_BASED_DELETE_SUCCESS, ""));
        } catch (Exception e) {
            response = buildExceptionErrorResponse(SystemOperation.DELETE, SystemMessages.LOCATION_BASED_DELETE_FAILED, e);
        }

        return response;
    }
}
