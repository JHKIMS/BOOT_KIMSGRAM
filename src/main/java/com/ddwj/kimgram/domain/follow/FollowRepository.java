package com.ddwj.kimgram.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    @Modifying // Insert, Delete, Update 를 네이티브 쿼리로 작성시 해당 어노테이션이 필요하다.
    @Query(value="Insert Into follow(fromUserId, toUserId, createDate) Values(:fromUserId, :toUserId, now())",
            nativeQuery = true)
    void mFollow(int fromUserId, int toUserId);

    @Modifying
    @Query(value="Delete From follow Where fromUserid=:fromUserId And toUserId=:toUserId",
            nativeQuery = true)
    void mUnFollow(int fromUserId, int toUserId);

    @Query(value="Select count(*) from follow where fromUserId=:principalId And toUserId=:pageUserId", nativeQuery = true)
    int mFollowState(int principalId, int pageUserId);
    // Count가 1이라면 구독된 상태
    
    @Query(value="Select count(*) from follow Where fromUserId=:pageUserId", nativeQuery = true)
    int mFollowCount(int pageUserId);
    // Count가 1이라면 해당 페이지의 주인이다.


}
