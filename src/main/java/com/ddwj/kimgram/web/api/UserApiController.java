package com.ddwj.kimgram.web.api;

import com.ddwj.kimgram.config.auth.PrincipalDetails;
import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.handler.ex.CustomValidationApiException;
import com.ddwj.kimgram.service.FollowService;
import com.ddwj.kimgram.service.UserService;
import com.ddwj.kimgram.web.dto.CMRespDto;
import com.ddwj.kimgram.web.dto.follow.FollowResDto;
import com.ddwj.kimgram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final FollowService followService;

    // 회원수정
    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id,
                               @Valid UserUpdateDto userUpdateDto,    // 여기서 검증을 해서
                               BindingResult bindingResult, // 문제가 있으면 여기에 다 담아라. + BindingResult는 @Valid가 적혀있는 다음 파라미터에 적는다.
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함.", errorMap);

        } else {
            User userEntity = userService.updateUser(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1, "회원수정완료", userEntity);
            /* 응답시에 userEntity의 모든 getter함수가 호출되고 JSON으로 파싱하여 응답한다.*/
        }
    }

    @GetMapping("api/user/{pageUserId}/follow") // 팔로우 리스트
    public ResponseEntity<?> followList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<FollowResDto> followResDto = followService.followerList(principalDetails.getUser().getId(), pageUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "팔로우 정보 리스트 가져오기 성공", followResDto), HttpStatus.OK);
    }

}
