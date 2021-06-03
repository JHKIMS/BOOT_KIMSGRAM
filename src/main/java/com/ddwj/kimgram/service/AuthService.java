package com.ddwj.kimgram.service;

import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    
    public User joinUser(User user){
        //회원가입 진행
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
