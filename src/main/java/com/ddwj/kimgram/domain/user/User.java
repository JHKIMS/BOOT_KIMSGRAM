package com.ddwj.kimgram.domain.user;

// JPA - 자바로 데이터를 영구적으로 저장할 수 있는 API를 제공

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String username;
    private String password;

    private String name;
    private String website;
    private String bio;
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;  // 회원 사진
    private String role; // 권한

    private LocalDateTime createDate;

    @PrePersist // 디비에 Insert 되기 직전에 실행한다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
