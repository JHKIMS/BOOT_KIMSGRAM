package com.ddwj.kimgram.domain.likes;

import com.ddwj.kimgram.domain.image.Image;
import com.ddwj.kimgram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        // 유니크 제약 조건 걸어주기(팔로우 중복을 막아주는 부분)
        uniqueConstraints ={
                @UniqueConstraint(
                        name="likes_uk",
                        columnNames = {"imageId", "userId"}
                )
        }
)
public class Likes { // N
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="imageId")
    @ManyToOne
    private Image image;  // 1개의 이미지는 100개의 좋아요가 있을 수 있다. 1

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name="userId")
    @ManyToOne
    private User user;

    private LocalDateTime createDate;

    @PrePersist // 디비에 Insert 되기 직전에 실행한다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
