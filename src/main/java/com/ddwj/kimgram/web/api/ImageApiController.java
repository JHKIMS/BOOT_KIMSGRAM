package com.ddwj.kimgram.web.api;

import com.ddwj.kimgram.config.auth.PrincipalDetails;
import com.ddwj.kimgram.domain.image.Image;
import com.ddwj.kimgram.service.ImageService;
import com.ddwj.kimgram.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;

    @GetMapping("/api/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<Image> images = imageService.imageStory(principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"성공", images), HttpStatus.OK);
    }
}
