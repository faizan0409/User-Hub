package com.epam.user_hub;

import com.epam.user_hub.dto.UserDto;
import com.epam.user_hub.entity.User;
import com.epam.user_hub.exception.UserException;
import com.epam.user_hub.repository.UserRepository;
import com.epam.user_hub.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserHubApplicationTests {

//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    UserServiceImpl userServiceImpl;
//
//    private User user;
//    private Address address1;
//    private Address address2;
//    private Role role1;
//    private Role role2;
//
//    @BeforeEach
//    void setUp() {
//        user = new User();
//        user.setUid(1L);
//        user.setEmail("faizan@epam.com");
//        user.setPassword("faizan@1234");
//        user.setName("Faizan");
//        user.setAge(23);
//        user.setGender("Male");
//        user.setTechStack("Java");
//        user.setReportingManager("Srinivas");
//
//        address1 = new Address(101, "Hyderabad", "office");
//        address2 = new Address(102, "Nashik", "home");
//        user.setAddresses(List.of(address1, address2));
//
//        role1 = new Role(1, "Junior Software Engineer");
//        role2 = new Role(2, "Trainee");
//        user.setRoles(List.of(role1, role2));
//    }
//
//    @Test
//    void testRegisterUserSuccess() {
//        when(userRepository.save(any(User.class))).thenReturn(new User(1L, "faizan@epam.com", "faizan@1234", "Faizan", 23, "Male", "Java", "Srinivas", List.of(address1, address2), List.of(role1, role2)));
//
//        User savedUser = userServiceImpl.registerUser(user);
//
//        assertEquals(user.getUid(), savedUser.getUid());
//        assertEquals(user.getEmail(), savedUser.getEmail());
//        assertEquals(user.getAddresses(), savedUser.getAddresses());
//        assertEquals(user.getRoles(), savedUser.getRoles());
//    }


        @InjectMocks
        UserServiceImpl userServiceImp;

        @Mock

        UserRepository mockRepository;

        @Mock

        ObjectMapper mockedObjectMapper;

//    @BeforeEach

//    public void setUp(){

//        userServiceImp= new UserServiceImp(mockRepository,mockedObjectMapper);

//    }

        @Test

        public void test_getUserById() throws Throwable {

            User mockUser= new User();

            mockUser.setUid(1);

            mockUser.setName("Faizan");

            Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.of(mockUser));

            User result= userServiceImp.searchUser(1L);

            Assertions.assertEquals(mockUser.getName(),result.getName());

            Assertions.assertEquals(mockUser.getUid(),result.getUid());

            Mockito.verify(mockRepository).findById(1L);

        }

        @Test

        public void test_getUserByIdNotFound(){

            Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.empty());

            Assertions.assertThrows(UserException.class,()-> userServiceImp.searchUser(1L),"User is not found for the ID:");

        }

        @Test

        public void testGetUserByIdFound() throws Throwable {

            User user = new User();

            user.setUid(1L);

            Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.of(user));

            User result = userServiceImp.searchUser(1L);

            Assertions.assertNotNull(result);

            Assertions.assertEquals(1, result.getUid());

        }

        @Test

        public void test_createUser(){

            User user = new User();

            UserDto userDto= new UserDto();

            Mockito.when(mockedObjectMapper.convertValue(Mockito.any(), Mockito.eq(User.class))).thenReturn(user);

            Mockito.when(mockRepository.save(user)).thenReturn(user);

            User result = userServiceImp.registerUser(userDto);

            Assertions.assertNotNull(result);

            Mockito.verify(mockRepository).save(user);

        }


    }


