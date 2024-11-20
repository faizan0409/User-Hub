package com.epam.user_hub.controller;

import com.epam.user_hub.dto.LoginDto;
import com.epam.user_hub.dto.TokenResponse;
import com.epam.user_hub.dto.UserDto;
import com.epam.user_hub.entity.User;
import com.epam.user_hub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    // Register User
    @PostMapping("register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) throws Throwable {
        User user = userService.registerUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

//    // Login
//    @PostMapping("login")
//    public String userLogin(@RequestBody LoginDto loginDto) {
//        return userService.UserLogin(loginDto);
//    }

    // Login
    @PostMapping("login")
    public ResponseEntity<TokenResponse<String>> UserLogin(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(
                new TokenResponse<>(userService.UserLogin(loginDto)), HttpStatus.OK);
    }

    //Search All Users
    @GetMapping
    public List<User> searchAllUsers() {
        return userService.searchAllUsers();
    }

    //Search All Users Pagination
    @GetMapping("pagenumber/{pagenumber}/pagesize/{pagesize}")
    public Page<User> searchUsers(@PathVariable("pagenumber") Integer pageNumber,
                                  @PathVariable("pagesize") Integer pageSize) throws Throwable {
        return userService.searchUsers(pageNumber, pageSize);
    }

    // Search User By ID
    @GetMapping("id/{uid}")
    public User searchUser(@PathVariable long uid) throws Throwable {
        return userService.searchUser(uid);
    }

    // Search User By Name
    @GetMapping("name/{name}")
    public User searchUser(@PathVariable String name) throws Throwable {
        return userService.searchUser(name);
    }

    // Update User
    @PutMapping
    public User updateUser(@Valid @RequestBody UserDto userDto)
            throws Throwable {
        return userService.updateUser(userDto);
    }

    // Delete User By ID
    @DeleteMapping("{uid}")
    public String deleteUserById(@PathVariable long uid) throws Throwable {
        return userService.deleteUser(uid);
    }

    // Delete All Users
    @DeleteMapping
    public String deleteAllUser() throws Throwable {
        return userService.deleteUsers();
    }

}
