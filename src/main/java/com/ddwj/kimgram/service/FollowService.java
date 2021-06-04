package com.ddwj.kimgram.service;

import com.ddwj.kimgram.domain.follow.FollowRepository;
import com.ddwj.kimgram.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void follow(int fromUserId, int toUserId){
        try {
            followRepository.mFollow(fromUserId, toUserId);
        }catch (Exception e){
            throw new CustomApiException("이미 구독을 했습니다.");
        }

    }

    @Transactional
    public void unFollow(int fromUserId, int toUserId){
        followRepository.mUnFollow(fromUserId, toUserId);
    }
}
