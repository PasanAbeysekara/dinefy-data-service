package com.thaprobit.resengine.controller.service;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.dao.WidenPropData;
import com.thaprobit.resengine.dao.key.WidenDataGridKey;
import com.thaprobit.resengine.repo.AvailDataRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Tharindu Aththanayake
 * @since 8/21/2020 23:28 PM
 */
@Service
@Slf4j
public class AvailDataService extends AbstractService<WidenPropData> {

    @Autowired
    private AvailDataRepository availDataRepository;


    // Update Avail Data
    public ResponseEntity<ResponseWrapper<WidenPropData>> updateAvailData(WidenPropData widenPropData) {
        try {
            widenPropData.calculateBookable();
            WidenPropData updatedWidenPropData = availDataRepository.save(widenPropData);
            return ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.MODIFY.withSuccess(), SystemMessages.AVAILABILITY_DATA_UPDATE_SUCCESS, updatedWidenPropData));
        } catch (Exception e) {
            log.error("Error updating availability data", e);
            return buildExceptionErrorResponse(SystemOperation.MODIFY, SystemMessages.AVAILABILITY_DATA_UPDATE_FAILED, e);
        }
    }

//    public ResponseEntity<ResponseWrapper<WidenPropData>> getAvailDataById(Long id) {
//
//    }

//    public ResponseEntity<ResponseWrapper<Iterable<WidenPropData>>> getAllAvailData() {
//        return ResponseEntity.ok()
//                .headers(addCommonHeaders(new HttpHeaders()))
//                .body(AvailDataRepository.findAll());
//    }

    public ResponseEntity<ResponseWrapper<List<WidenPropData>>> getAllAvailData() {
        try {
            List<WidenPropData> allWidenPropData = availDataRepository.findAll();

            if (!allWidenPropData.isEmpty()) {
                return ResponseEntity.ok()
                        .headers(addCommonHeaders(new HttpHeaders()))
                        .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, allWidenPropData));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(addCommonHeaders(new HttpHeaders()))
                        .body(new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND));
            }
        } catch (Exception e) {
            log.error("Error retrieving all availability data", e);
            List<WidenPropData> emptyList = Collections.emptyList();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.INVALID_DATA, emptyList));
        }
    }

    public ResponseEntity<ResponseWrapper<WidenPropData>> getAvailDataById(WidenDataGridKey id) {
        try {
            Optional<WidenPropData> optionalWidenPropData = availDataRepository.findById(id);

            if (optionalWidenPropData.isPresent()) {
                WidenPropData widenPropData = optionalWidenPropData.get();
                return ResponseEntity.ok()
                        .headers(addCommonHeaders(new HttpHeaders()))
                        .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, widenPropData));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(addCommonHeaders(new HttpHeaders()))
                        .body(new ResponseWrapper<>(SystemOperation.READ.withError(), SystemMessages.NOT_FOUND));
            }
        } catch (Exception e) {
            log.error("Error retrieving availability data", e);
            return buildExceptionErrorResponse(SystemOperation.READ, SystemMessages.INVALID_DATA, e);
        }
    }


}
