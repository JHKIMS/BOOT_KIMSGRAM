package com.ddwj.kimgram.domain.image;

import com.ddwj.kimgram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
// 게시물이라고 생각하면 될듯
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 나는 MariaDB를 쓰고 있기 때문에, 번호 증가 전략을 auto_increment로 사용한다.
    private int id;

    private String caption;

    private String postImageUrl;
    // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 INSERT

    @JoinColumn(name="userId")
    @ManyToOne
    private User user; // 누가 업로드 하는지 알아야하기 때문에 User가 필요하다.
    //한 명의 유저는 여러개의 사진(게시물)을 등록할 수 있다.

    private LocalDateTime createDate;

    @PrePersist // 디비에 Insert 되기 직전에 실행한다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
