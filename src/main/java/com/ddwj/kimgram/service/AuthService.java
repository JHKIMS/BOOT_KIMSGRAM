package com.ddwj.kimgram.service;

import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional  // Insert Update Delete 할때 사용한다.
    public User joinUser(User user){
        //회원가입 진행
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        user.setRole("ROLE_USER");

        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
