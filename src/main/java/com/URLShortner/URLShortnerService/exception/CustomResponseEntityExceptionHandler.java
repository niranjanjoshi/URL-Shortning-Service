package com.URLShortner.URLShortnerService.exception;

import com.URLShortner.URLShortnerService.model.UrlExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidURLException.class)
    public final ResponseEntity<Object> handleGenericExceptions(InvalidURLException ex,
                                                                WebRequest request)
    {
        UrlExceptionResponse exceptionResponse = new UrlExceptionResponse(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public final ResponseEntity<Object> handleGenericExceptions(GenericException ex,
                                                                WebRequest request)
    {
        UrlExceptionResponse exceptionResponse = new UrlExceptionResponse(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AliasFieldException.class)
    public final ResponseEntity<Object> handleGenericExceptions(AliasFieldException ex,
                                                                WebRequest request)
    {
        UrlExceptionResponse exceptionResponse = new UrlExceptionResponse(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
