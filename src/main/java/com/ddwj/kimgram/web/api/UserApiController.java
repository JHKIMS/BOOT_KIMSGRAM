package com.ddwj.kimgram.web.api;

import com.ddwj.kimgram.config.auth.PrincipalDetails;
import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.handler.ex.CustomValidationApiException;
import com.ddwj.kimgram.handler.ex.CustomValidationException;
import com.ddwj.kimgram.service.UserService;
import com.ddwj.kimgram.web.dto.CMRespDto;
import com.ddwj.kimgram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    // 회원수정
    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult, // BindingResult는 @Valid가 적혀있는 다음 파라미터에 적는다.
                               @AuthenticationPrincipal PrincipalDetails principalDetails){

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함.", errorMap);

        }else{
            User userEntity = userService.updateUser(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1,"회원수정완료",userEntity);
        }


    }
}