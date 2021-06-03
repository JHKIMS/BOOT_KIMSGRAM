package com.ddwj.kimgram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션 없이도 IOC 자동 등록이 된다.
public interface UserRepository extends JpaRepository<User, Integer> {
}
