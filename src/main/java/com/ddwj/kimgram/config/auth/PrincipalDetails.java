package com.ddwj.kimgram.config.auth;

import com.ddwj.kimgram.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;

    public PrincipalDetails(User user) {
        this.user=user;
    }

    @Override   // 권한을 가져오는 함수이다.
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>();
/*        collector.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });*/ // 밑에 람다식으로 변경해보자.

        collector.add(() -> {
            return user.getRole();
        });

        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override // 계정이 만료가 되었니?
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정이 잠겼니?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호를 한번은 바꿔야 하지 않겠니?
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 활성화되었니?
    public boolean isEnabled() {
        return true;
    }
}
