package com.ddwj.kimgram.web.dto.image;


import com.ddwj.kimgram.domain.image.Image;
import com.ddwj.kimgram.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile file;

    private String caption;

    // imageUploadDto를 image에 집어넣을 수 있게끔 해주는 로직이다.
    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
