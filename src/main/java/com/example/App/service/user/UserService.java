package com.example.App.service.user;

import com.example.App.dto.UserDto;
import com.example.App.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findByEmail(String email);
    List<UserDto> findAllUsers();
}
