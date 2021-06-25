package com.ddwj.kimgram.domain.user;

// JPA - 자바로 데이터를 영구적으로 저장할 수 있는 API를 제공

import com.ddwj.kimgram.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 나는 MariaDB를 쓰고 있기 때문에, 번호 증가 전략을 auto_increment로 사용한다.
    private int id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;

    @Column(nullable = false)
    private String email;

    private String phone;
    private String gender;

    private String profileImageUrl;  // 회원 사진
    private String role; // 권한

    /*양방향 매핑 :
    한명의 유저는 여러개의 이미지를 가질 수 있다.
     나는 연관관계의 주인이 아니다. --> 연관관계의 주인은 Image테이블에 user이다. 테이블에 컬럼을 생성하지 마라.
    User를 Select할때 해당 User id로 등록된 image들을 (전부 조인해서) 다 가져와.(EAGER전략)
     FetchType의 기본값은 LAZY다
     -> User를 select할 때 해당 User id로 등록된 image들을 가져오지마 - 대신 getImages()함수를 호출할 때 가져와라.*/
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist // 디비에 Insert 되기 직전에 실행한다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
