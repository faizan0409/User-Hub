package com.epam.user_hub.service;

import com.epam.user_hub.dto.LoginDto;
import com.epam.user_hub.dto.UserDto;
import com.epam.user_hub.entity.User;
import com.epam.user_hub.exception.UserException;
import com.epam.user_hub.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    // Register User
    @Override
    public User registerUser(UserDto userDto) {

        User user = this.objectMapper.convertValue(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception exception) {
            log.error("Error occurred while creating an user");
            throw new UserException("User can not be created with duplicate email", HttpStatus.CONFLICT);

        }
        return user;
    }

    // Login
    public String UserLogin(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));
        log.info("Login Successfully!");
        return this.tokenService.generateToken(authentication);
    }

    //Search All Users
    public List<User> searchAllUsers() {
        return userRepository.findAll();
    }

    //Search All Users - Pagination
    @Override
    public Page<User> searchUsers(Integer pageNumber, Integer pageSize) throws Throwable {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    // Search User By ID
    @Override
    public User searchUser(long uid) throws Throwable {
        return userRepository.findById(uid)
                .orElseThrow(() -> new UserException("User not found with ID: " + uid, HttpStatus.NOT_FOUND));
    }

    // Search User By Name
    @Override
    public User searchUser(String name) throws Throwable {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserException("User not found in Database with name :" + name, HttpStatus.NOT_FOUND));
    }

    // Update User
    @Override
    public User updateUser(UserDto userDto) throws Throwable {
        User user = this.objectMapper.convertValue(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.findById(user.getUid()).orElseThrow(() ->
                new UserException("User not found with Id:" + user.getUid(), HttpStatus.NOT_FOUND)
        );
        return userRepository.save(user);
    }

    // Delete User By ID
    @Override
    public String deleteUser(long uid) throws Throwable {
        userRepository.findById(uid).orElseThrow(() ->
                new UserException("User not found with Id:" + uid, HttpStatus.NOT_FOUND)
        );
        userRepository.deleteById(uid);
        return "User deleted with ID: " + uid;
    }

    // Delete All User
    @Override
    public String deleteUsers() throws Throwable {
        userRepository.deleteAll();
        return "All Users deleted..";
    }

}
