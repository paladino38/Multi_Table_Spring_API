package org.gm2.pdv.loombok_tst.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ResponseDTO<T> {
    @Getter
    private List<String> messages;
    @Getter
    private T data;

    public ResponseDTO(List<String> messages, T data) {
        this.messages = messages;
        this.data = data;
    }
    public ResponseDTO(String message, T data, HttpStatus ok) {
        this.messages = Arrays.asList(message);
        this.data = data;
    }
}
