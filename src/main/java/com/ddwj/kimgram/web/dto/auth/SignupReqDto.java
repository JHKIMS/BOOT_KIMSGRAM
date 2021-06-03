package com.ddwj.kimgram.web.dto.auth;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
public class SignupReqDto {

    private String username;
    private String password;
    private String email;
    private String name;

}
