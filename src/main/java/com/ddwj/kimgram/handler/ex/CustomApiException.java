package com.ddwj.kimgram.handler.ex;


// 우선은 팔로우 기능할때 처리할 때 사용하기 위해 만듬
public class CustomApiException extends RuntimeException {

    // 객체를 구분할 때 사용한다.
    private static final long serialVersionUID=1L;

    public CustomApiException(String message) {
        super(message);
    }
}
