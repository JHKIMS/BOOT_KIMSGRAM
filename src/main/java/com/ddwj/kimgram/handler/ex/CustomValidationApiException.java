package com.ddwj.kimgram.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException {

    // 객체를 구분할 때 사용한다.
    private static final long serialVersionUID=1L;

    private Map<String, String> errorMap;

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap(){
        return errorMap;
    }

    //유효성 처리를 하기 위해서 추가하는 부분
    public CustomValidationApiException(String message) {
        super(message);
    }
}
