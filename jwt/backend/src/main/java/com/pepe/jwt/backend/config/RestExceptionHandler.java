package com.pepe.jwt.backend.config;

import com.pepe.jwt.backend.dto.ErrorDTO;
import com.pepe.jwt.backend.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> handlerException(AppException ex) {

        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorDTO(ex.getMessage()));
    }
}
