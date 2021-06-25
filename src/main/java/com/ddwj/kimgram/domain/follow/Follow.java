package com.ddwj.kimgram.domain.follow;

import com.ddwj.kimgram.domain.user.User;
import lombok.*;

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
                        name="follow_uk",
                        columnNames = {"fromUserId", "toUserId"}
                        )
        }
)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="fromUserId") // 컬러명을 이렇게 만들어라
    @ManyToOne
    private User fromUser;

    @JoinColumn(name="toUserId") // 컬러명을 이렇게 만들어라
    @ManyToOne
    private User toUser;

    private LocalDateTime createDate;

    @PrePersist // 디비에 Insert 되기 직전에 실행한다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
