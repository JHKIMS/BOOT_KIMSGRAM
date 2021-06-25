package com.ddwj.kimgram.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select * from image where userId in (select toUserId from follow where fromUserId=:principalId);", nativeQuery = true)
    List<Image> mStory(int principalId);
}
