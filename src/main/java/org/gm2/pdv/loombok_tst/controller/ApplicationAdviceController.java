package org.gm2.pdv.loombok_tst.controller;


import org.gm2.pdv.loombok_tst.dto.ResponseDTO;
import org.gm2.pdv.loombok_tst.exception.InvalidOperationException;
import org.gm2.pdv.loombok_tst.exception.NoItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ApplicationAdviceController {
    @ExceptionHandler(NoItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleNoItemException(NoItemException ex) {
        String message = ex.getMessage();
        return new ResponseDTO(Collections.singletonList(message));
    }


    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleInvalidOperationException(InvalidOperationException ex) {
        String message = ex.getMessage();
        return new ResponseDTO(Collections.singletonList(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleValidationException(MethodArgumentNotValidException ex) {
        List<String> erros = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            erros.add(errorMessage);
        });
        return new ResponseDTO(erros);
    }
}
