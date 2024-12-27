package com.ttung.commonservice.advide;

import com.ttung.commonservice.common.CommonException;
import com.ttung.commonservice.common.ErrorMessage;
import com.ttung.commonservice.common.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.ValidationException;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionAdvide {
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception ex){
        log.error("Unknow internal error"+ex.getMessage());
        log.error("Exception class"+ex.getClass());
        log.error("Exception cause"+ex.getCause());
        return new ResponseEntity(new ErrorMessage("999", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handlerCommonException(CommonException ex){
        log.error(String.format("Common error: %s %s %s", ex.getCode(), ex.getMessage(), ex.getStatus()));
        return new ResponseEntity(new ErrorMessage(ex.getCode(), ex.getMessage(), ex.getStatus()), ex.getStatus());
    }
    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleValidateException(ValidateException ex){
        return new ResponseEntity(ex.getMessagesMap(), ex.getStatus());
    }
}

