package org.Marias.BeautyAgenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(EntidadNoEncontradaException.class)
public ResponseEntity<ErrorResponseDTO> handleClientaNoEncontradaException(EntidadNoEncontradaException ex){
    ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(),
                            LocalDateTime.now(), ex.getMessage());

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

}
@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
    ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}


}
