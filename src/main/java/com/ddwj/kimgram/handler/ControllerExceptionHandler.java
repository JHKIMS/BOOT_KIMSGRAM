package com.ddwj.kimgram.handler;

import com.ddwj.kimgram.handler.ex.CustomApiException;
import com.ddwj.kimgram.handler.ex.CustomValidationApiException;
import com.ddwj.kimgram.handler.ex.CustomValidationException;
import com.ddwj.kimgram.util.Script;
import com.ddwj.kimgram.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice // 이 어노테이션이 붙으므로 이 해당 클래스가 모든 Exception을 낚아챈다.
@RestController
public class ControllerExceptionHandler {

/*
    CMResponseDto를 만들어서 처리한 부분 : AJAX를 사용할 때 결국 이 방법을 사용하게 된다.
    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto<?> validationException(CustomValidationException e) {
        return new CMRespDto<Map<String,String>>(-1,e.getMessage(), e.getErrorMap());
    }*/

    // 클라이언트에게 응답할 때는 Script인 이 방식이 좋다.
    @ExceptionHandler(CustomValidationException.class)  // 사용자에게 이 방법이 더 좋다.
    public String validationException(CustomValidationException e) {
        if(e.getErrorMap() == null){
            return Script.back(e.getMessage());
        }else{
            return Script.back(e.getErrorMap().toString());
        }

    }


    // 뒷단 막기 위해서 추가한 핸들러 - 유효성 실패 :얘는 데이터를 리턴한다.
    @ExceptionHandler(CustomValidationApiException.class)  // 사용자에게 이 방법이 더 좋다.
    public ResponseEntity<?> validationException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    // 우선은 팔로우 부분을 위한 Exception처리
    @ExceptionHandler(CustomApiException.class) 
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }
}
