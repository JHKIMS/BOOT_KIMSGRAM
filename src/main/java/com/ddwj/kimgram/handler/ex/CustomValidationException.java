package com.ddwj.kimgram.handler.ex;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

public class CustomValidationException extends RuntimeException {

    // 객체를 구분할 때 사용한다.
    private static final long serialVersionUID=1L;

    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap(){
        return errorMap;
    }
}
