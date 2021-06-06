package com.ddwj.kimgram.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException {

    // 객체를 구분할 때 사용한다.
    private static final long serialVersionUID=1L;


    public CustomException(String message, Map<String, String> errorMap) {
        super(message);
    }


}
