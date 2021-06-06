package com.ddwj.kimgram.web.dto.user;

import com.ddwj.kimgram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

    private int imageCount;
    private boolean pageOwnerState; // 페이지 주인 유저
    private User user;

}
