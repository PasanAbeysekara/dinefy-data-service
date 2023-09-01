package com.thaprobit.resengine.interceptor;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.util.Error;
import com.thaprobit.util.ErrorUtility;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/21/2020 8:59 PM
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class WrappedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Error error = ErrorUtility.exceptionErrorMapper(SystemMessages.BAD_REQUEST, ex, false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(headers)
                .body(new ResponseWrapper<>(SystemOperation.Deserialize, SystemMessages.BAD_REQUEST, error));
    }


    //other exception handlers below

}