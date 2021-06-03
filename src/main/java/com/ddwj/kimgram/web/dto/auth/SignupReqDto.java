package com.ddwj.kimgram.web.dto.auth;

import com.ddwj.kimgram.domain.user.User;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
public class SignupReqDto {

    private String username;
    private String password;
    private String email;
    private String name;


    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
