package com.ddwj.kimgram.service;

import com.ddwj.kimgram.config.auth.PrincipalDetails;
import com.ddwj.kimgram.domain.image.Image;
import com.ddwj.kimgram.domain.image.ImageRepository;
import com.ddwj.kimgram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;


    //이미지 경로 가져오기
    @Value("${file.path}") // yml파일에 file: path: 라고 해놓았다.
    private String uploadFolder;


    // 서비스 단에서 디비에 변경을 줄 때는 항상 트랜잭션을 걸어줘야 한다.
    /* 로직 하나가 터지면 전부 롤백시켜준다.*/
    @Transactional
    public void photoUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

        UUID uuid = UUID.randomUUID(); // 고유성이 보장된다.
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
        // 혹시 몰라서 위와 같이 imageFileName을 만들어주었다.

        Path imageFilePath = Paths.get(uploadFolder + imageFileName); // 경로+파일명

        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
            // 통신중 or 하드디스크에서 읽어오는 과정에서 오류가 날 경우를 대비해서.
        }catch(Exception e){
            e.printStackTrace();
        }

        
        // 이미지 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }

    @Transactional(readOnly = false) // 더티체킹을 안한다.
    public Page<Image> imageStory(int principalId, Pageable pageable){
        Page<Image> images = imageRepository.mStory(principalId, pageable);

        // images에 좋아요 상태 담기
        images.forEach((image)->{

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like) ->{
                if(like.getUser().getId() == principalId) { // 해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한 것인지 비교
                    image.setLikeState(true);
                }
            });
        });
        return images;
    }


}
