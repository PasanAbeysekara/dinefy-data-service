package com.solution.x.data.controller.service.sys;

import com.solution.x.dao.sys.Choices;
import com.solution.x.dao.sys.PropertySpeciality;
import com.solution.x.data.controller.assembler.ChoiceModelAssembler;
import com.solution.x.data.controller.service.HATEOASProvider;
import com.solution.x.data.facade.dto.ChoiceModel;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.sys.ChoicesRepository;
import com.solution.x.repo.sys.PropertySpecialityRepository;
import com.solution.x.service.AbstractService;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharindu Aththanayake
 */
@Service
@Slf4j
public class PropSpecialityService extends AbstractService<PropertySpeciality> {

    @Autowired
    private PropertySpecialityRepository propertySpecialityRepository;

    /**
     * Get All Specialities
     *
     * @return all Specialities
     */
    public ResponseEntity<ResponseWrapper<List<PropertySpeciality>>> getSpecialities()
    {
        List<PropertySpeciality> specialities = propertySpecialityRepository.findAll();

        return ResponseEntity.ok()
                .headers( addCommonHeaders( new HttpHeaders() ) )
                .body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, specialities ) );
    }

    /**
     * Get Single Speciality
     *
     * @param id Speciality ID
     * @return The Speciality
     */
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> getSpeciality( short id )
    {
        Optional<PropertySpeciality> optionalSpeciality = propertySpecialityRepository.findById( id );

        ResponseEntity<ResponseWrapper<PropertySpeciality>> response;

        if( optionalSpeciality.isPresent() )
        {
            PropertySpeciality speciality = optionalSpeciality.get();

            response = ResponseEntity.ok()
                    .headers( addCommonHeaders( new HttpHeaders() ) )
                    .body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, speciality ) );
        }
        else
        {
            response = buildNotFoundResponseWrapped();
        }

        return response;

    }

    /**
     * Create a Speciality
     *
     * @param propertySpeciality The Speciality
     * @return Saved Speciality response
     */
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> createSpeciality( PropertySpeciality propertySpeciality )
    {
        ResponseEntity<ResponseWrapper<PropertySpeciality>> response;

        try
        {
            PropertySpeciality savedSpeciality = propertySpecialityRepository.save( propertySpeciality );

            response = ResponseEntity.status( HttpStatus.CREATED )
                    .headers( addCommonHeaders( new HttpHeaders() ) )
                    .body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.SPECIALITY_CREATE_SUCCESS, savedSpeciality ) );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.SPECIALITY_CREATE_FAILED, e );
        }

        return response;
    }


    /**
     * Update a Speciality
     *
     * @param id   The Speciality ID
     * @param propertySpeciality The Speciality
     * @return Updated Speciality response
     */
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> updateSpeciality( short id, PropertySpeciality propertySpeciality )
    {
        ResponseEntity<ResponseWrapper<PropertySpeciality>> response;

        try
        {
            propertySpeciality.setSpecialityId( id );
            PropertySpeciality savedSpeciality = propertySpecialityRepository.save( propertySpeciality );

            response = ResponseEntity.ok()
                    .headers( addCommonHeaders( new HttpHeaders() ) )
                    .body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.SPECIALITY_UPDATE_SUCCESS, savedSpeciality ) );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.SPECIALITY_UPDATE_FAILED, e );
        }

        return response;
    }

    /**
     * Delete a Speciality
     *
     * @param id The Speciality ID
     * @return Delete Speciality response
     */
    public ResponseEntity<ResponseWrapper<PropertySpeciality>> deleteSpeciality( short id )
    {
        ResponseEntity<ResponseWrapper<PropertySpeciality>> response;

        try
        {
            propertySpecialityRepository.deleteById( id );

            response = ResponseEntity.ok()
                    .headers( addCommonHeaders( new HttpHeaders() ) )
                    .body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.SPECIALITY_DELETE_SUCCESS, "" ) );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.SPECIALITY_DELETE_FAILED, e );
        }

        return response;
    }
}
