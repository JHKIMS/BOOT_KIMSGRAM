package com.ddwj.kimgram.service;

import com.ddwj.kimgram.domain.follow.FollowRepository;
import com.ddwj.kimgram.handler.ex.CustomApiException;
import com.ddwj.kimgram.web.dto.follow.FollowResDto;
import lombok.RequiredArgsConstructor;

import org.qlrm.mapper.JpaResultMapper;
// 별도로 의존성을 추가해줘야한다.
// QLRM이란 : 디비에서 리절트된 결과를 자바 클래스에 매핑해주는 라이브러리이다.

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final EntityManager em;  // 모든 Repository는 EntityManager의 구현체이다.

    @Transactional
    public void follow(int fromUserId, int toUserId) {
        try {
            followRepository.mFollow(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 팔로우함.");
        }

    }

    @Transactional
    public void unFollow(int fromUserId, int toUserId) {
        followRepository.mUnFollow(fromUserId, toUserId);
    }


    @Transactional(readOnly = true)
    public List<FollowResDto> followerList(int principalId, int pageUserId){

        // 쿼리의 준비 단계
        StringBuffer sb = new StringBuffer();
        sb.append("select u.id, u.username, u.profileImageUrl, ");
        sb.append("if((select 1 from follow where fromUserId = ? and toUserId = u.id), 1, 0) followState, ");
        sb.append("if((?=u.id), 1, 0) equalUserState ");
        sb.append("from user u inner join follow f ");
        sb.append("on u.id = f.toUserId ");
        sb.append("where f.fromUserId=?"); // 세미콜론은 생략해야 한다.

        // 1. fromUserId=? and toUserId 의 물음표 : principalId
        // 2. if((?=u.id)의 물음표 : principalId
        // 3. 마지막 물음표 : pageUserId

        //쿼리를 완성하는 단계
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        // 쿼리 실행(qlrm 라이브러리가 필요하다)
        JpaResultMapper result = new JpaResultMapper();
        List<FollowResDto> followResDtos = result.list(query, FollowResDto.class);

        return followResDtos;
    }

}
