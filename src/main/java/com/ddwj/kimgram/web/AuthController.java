package com.ddwj.kimgram.web;

import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.service.AuthService;
import com.ddwj.kimgram.web.dto.auth.SignupReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class AuthController {

    private final AuthService authService;


    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignupReqDto signupReqDto) {   // form으로 데이터를 받으면 key=value 형식으로 받는다.

        log.info(signupReqDto.toString());

        User user = signupReqDto.toEntity();
        log.info(user.toString());

        User userEntity = authService.joinUser(user);

        return "auth/signin";
        // 페이지 이동이 아니라 실제로 회원가입을 한다.
        // -> 회원가입을 성공했으니깐 로그인을 하라고 signin페이지로 보내준다.
    }
}
