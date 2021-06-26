package com.ddwj.kimgram.service;

import com.ddwj.kimgram.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void clickLike(int imageId, int principalId){
        likesRepository.mLikes(imageId, principalId);
    }

    @Transactional
    public void cancleLike(int imageId, int principalId){
        likesRepository.mUnLikes(imageId, principalId);
    }
}
