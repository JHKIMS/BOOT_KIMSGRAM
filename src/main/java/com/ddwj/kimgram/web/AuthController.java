package com.ddwj.kimgram.web;

import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.handler.ex.CustomValidationException;
import com.ddwj.kimgram.service.AuthService;
import com.ddwj.kimgram.web.dto.auth.SignupReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
public class AuthController {

    private final AuthService authService;


    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupReqDto signupReqDto, BindingResult bindingResult) {   // form으로 데이터를 받으면 key=value 형식으로 받는다.
        // @Valid 로 체크해서 오류나는 것들을 bindingResult에 다 담아준다. 그걸 받는게 fieldErrors다.
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함.", errorMap);
        }
        else {
            log.info(signupReqDto.toString());

            User user = signupReqDto.toEntity();
            log.info(user.toString());

            User userEntity = authService.joinUser(user);

            return "auth/signin";
            // 페이지 이동이 아니라 실제로 회원가입을 한다.
            // -> 회원가입을 성공했으니깐 로그인을 하라고 signin페이지로 보내준다.
        }
    }
}
