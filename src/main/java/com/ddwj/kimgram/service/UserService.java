package com.ddwj.kimgram.service;

import com.ddwj.kimgram.domain.follow.FollowRepository;
import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.domain.user.UserRepository;
import com.ddwj.kimgram.handler.ex.CustomValidationApiException;
import com.ddwj.kimgram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional //  회원수정 부분
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

    // 이미지 업로드 부분
    @Transactional(readOnly = true) // 불필요한 연산을 하지 않는다.
    public UserProfileDto userProfile(int pageUserId, int principalId){
        UserProfileDto profileDto = new UserProfileDto();

        // select * from image where userId=:userId;
        User userEntity = userRepository.findById(pageUserId)
                .orElseThrow(()->{
                    throw new CustomValidationApiException("해당 프로필 페이지는 없는 페이지입니다.");
                });

        profileDto.setUser(userEntity);
        profileDto.setPageOwnerState(pageUserId==principalId); // 1은 페이지 주인 , -1은 주인이 아니다.
        profileDto.setImageCount(userEntity.getImages().size());

        int followState = followRepository.mFollowState(principalId, pageUserId);
        int followCount = followRepository.mFollowCount(pageUserId);

        profileDto.setFollowState(followState == 1);
        profileDto.setFollowCount(followCount);


        return profileDto;
    }

}
