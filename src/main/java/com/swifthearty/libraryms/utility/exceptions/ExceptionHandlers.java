package com.swifthearty.libraryms.utility.exceptions;


import com.swifthearty.libraryms.dto.response.BadRequestResponse;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.utility.mapper.ExceptionMapper;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ResourcesAlreadyExistException.class)
    public GeneralResponse handleUserAlreadyExistException(ResourcesAlreadyExistException e) {
        return ExceptionMapper.mapExceptionToResponse(e.getMessage());
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    public GeneralResponse handleResourcesNotFoundException(ResourcesNotFoundException e) {
        return ExceptionMapper.mapExceptionToResponse(e.getMessage());
    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public GeneralResponse handleIllegalArgumentException(IllegalArgumentException e) {
//        return ExceptionMapper.mapExceptionToResponse(e.getMessage());
//    }
//
//    @ExceptionHandler(MalformedInputException.class)
//    public GeneralResponse handleMalformedInputException(MalformedInputException e) {
//        return ExceptionMapper.mapExceptionToResponse(e.getMessage());
//    }
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        return e.getMessage();
//    }
//    @ExceptionHandler(HttpMessageNotWritableException.class)
//    public String handleHttpMessageNotWritableException(HttpMessageNotWritableException e) {
//        return e.getMessage();
//    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<String> handleForbidden(HttpClientErrorException.Forbidden ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Custom forbidden message: " + ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BadRequestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ExceptionMapper.mapBadRequestToResponse(errors);
    }

}
