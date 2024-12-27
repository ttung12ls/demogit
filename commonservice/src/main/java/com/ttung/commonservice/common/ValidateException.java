package com.ttung.commonservice.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ValidateException extends RuntimeException {
    private final String code;
    private final Map<String,String> messagesMap;
    private final HttpStatus status;

    public ValidateException(String code, Map<String, String> messagesMap, HttpStatus status) {
        this.code = code;
        this.messagesMap = messagesMap;
        this.status = status;
    }

}
