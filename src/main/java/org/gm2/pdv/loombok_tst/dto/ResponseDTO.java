package org.gm2.pdv.loombok_tst.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ResponseDTO<T> {
    @Getter
    private List<String> messages;


    public ResponseDTO(List<String> messages) {
        this.messages = messages;

    }
    public ResponseDTO(String message, HttpStatus ok) {
        this.messages = Arrays.asList(message);

    }
}
