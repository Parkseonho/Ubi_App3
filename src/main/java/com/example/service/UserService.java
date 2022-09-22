package com.example.service;

import com.example.dao.UserRepository;
import com.example.domain.User;
import com.example.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void join(User user) {
        userRepository.save(user);
    }

    public UserDto login(User user) throws Exception {
        Optional<User> opUser = userRepository.findByEmail(user.getEmail());
        if (opUser.isPresent()) {
            User loginedUser = opUser.get();
            if (loginedUser.getPassword().equals(user.getPassword())) {
                UserDto userDto = new UserDto();
                userDto.setId(loginedUser.getId());
                userDto.setEmail(loginedUser.getEmail());
                userDto.setUsername(loginedUser.getUsername());
                return userDto;
            }
            throw new Exception();
        }
        throw new Exception();
    }
}
