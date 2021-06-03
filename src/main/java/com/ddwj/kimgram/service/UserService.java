package com.ddwj.kimgram.service;

import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.domain.user.UserRepository;
import com.ddwj.kimgram.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User updateUser(int id, User user){
        // 1. 영속화
        User userEntity = userRepository.findById(id)   // 만약 이 예외를 터뜨려보고 싶은 경우에는 id부분에 임의로 숫자를 지정해서 넣어주면 된다.
                .orElseThrow(() -> {
                    return new CustomValidationApiException("찾을 수 없는 ID입니다.");});
        // get() : 찾았다   // 못찾은 경우 예외 발생시킨다. orElseThrow

        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity; // 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
    }
}
