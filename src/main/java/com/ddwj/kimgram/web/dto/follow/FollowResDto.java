package com.ddwj.kimgram.web.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowResDto {

    private int id;
    private String username;
    private String profileImageUrl;
    private Integer followState; // 팔로우를 하고 있는지
    private Integer equalUserState; // 동일 유저인지

}

