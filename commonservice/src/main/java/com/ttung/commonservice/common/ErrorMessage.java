package com.ttung.commonservice.common;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String code;
    private String message;
    private HttpStatus status;

}
