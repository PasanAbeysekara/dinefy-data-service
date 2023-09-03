package com.thaprobit.resengine.controller.service;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.dao.WidenPropData;
import com.thaprobit.resengine.repo.AvailDataRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/**
 * @author Tharindu Aththanayake
 * @since 8/21/2020 23:28 PM
 */
@Service
@Slf4j
public class AvailDataService extends AbstractService<WidenPropData> {

    @Autowired
    private AvailDataRepository availDataRepository;

    /**
     * Update Avail Data
     *
     * @param widenPropData The WidenPropData
     * @return Updated Availability Data
     */
    public ResponseEntity<ResponseWrapper<WidenPropData>> updateAvailData(WidenPropData widenPropData) {

        ResponseEntity<ResponseWrapper<WidenPropData>> response = null;

        try {

            widenPropData.calculateBookable();
            WidenPropData updatedWidenPropData = availDataRepository.save(widenPropData);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.MODIFY.withSuccess(), SystemMessages.AVAILABILITY_DATA_UPDATE_SUCCESS, updatedWidenPropData));

        } catch (Exception e) {

            e.printStackTrace();
            response = buildExceptionErrorResponse(SystemOperation.MODIFY, SystemMessages.AVAILABILITY_DATA_UPDATE_FAILED, e);

        }

        return response;

    }

}
