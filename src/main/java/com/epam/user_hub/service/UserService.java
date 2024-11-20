package com.epam.user_hub.service;

import com.epam.user_hub.dto.LoginDto;
import com.epam.user_hub.dto.UserDto;
import com.epam.user_hub.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User registerUser(UserDto userDto);

    Page<User> searchUsers(Integer pageNumber, Integer pageSize) throws Throwable;

    User searchUser(long uid) throws Throwable;

    User searchUser(String name) throws Throwable;

    User updateUser(UserDto userDto) throws Throwable;

    String deleteUser(long uid) throws Throwable;

    String deleteUsers() throws Throwable;

    public List<User> searchAllUsers();

    public String UserLogin(LoginDto loginDto);
}
