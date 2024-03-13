package com.hemoclick.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NumeroPaginasException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNumeroPaginasException(NumeroPaginasException ex) {
        ExceptionResponseDTO errorResponse = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TamanhoPaginasException.class)
    public ResponseEntity<ExceptionResponseDTO> handleTamanhoPaginasException(TamanhoPaginasException ex) {
        ExceptionResponseDTO errorResponse = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioJaAssociadoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioJaAssociadoException(UsuarioJaAssociadoException ex) {
        ExceptionResponseDTO errorResponse = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        ExceptionResponseDTO errorResponse = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoadorNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDoadorNaoEncontradoException(DoadorNaoEncontradoException ex) {
        ExceptionResponseDTO errorResponse = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
