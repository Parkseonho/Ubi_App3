package com.example.controller;

import com.example.dao.UserRepository;
import com.example.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/join")
    public String join(@RequestBody User user){
        user.setPassword(user.getPassword());
        user.setRoles("ROLE_UESR");
        userRepository.save(user);
        return "회원가입 완료";
    }
}
