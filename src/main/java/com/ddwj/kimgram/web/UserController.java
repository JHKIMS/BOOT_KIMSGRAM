package com.ddwj.kimgram.web;

import com.ddwj.kimgram.config.auth.PrincipalDetails;
import com.ddwj.kimgram.domain.user.User;
import com.ddwj.kimgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}") // 프로필 보기
    public String profile(@PathVariable int id, Model model) {
        User userEntity = userService.userProfile(id);

        model.addAttribute("user", userEntity);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return "user/update";
    }
}
