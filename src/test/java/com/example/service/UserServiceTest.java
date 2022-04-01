package com.example.service;

import com.example.demoshop.model.User;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    User user;

    @BeforeEach
    void init() {
        user = new User();
        user.setName("Name");
        user.setEmail("a@b");
        user.setActive(true);
        user.setPhoneNumber("123456");
        user.setLogin("login");
        user.setId(1L);
        user.setPassword("123");
    }


    @Test
    void shouldCreateUser() {
        //given
        when(passwordEncoder.encode("123")).thenReturn("123");
        when(userRepository.save(user)).thenReturn(null);

        //when
        boolean result = userService.createUser(this.user);

        //then
        assertNotNull(this.user);
        assertTrue(result);

        verify(passwordEncoder, Mockito.times(1)).encode("123");
        verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void shouldGetAllUsers() {
        //Given
        when(userRepository.findAll()).thenReturn(List.of(user));

        //when
        List<User> getAllUsers = userService.getAllUsers();

        //then
        assertNotNull(getAllUsers);
        }

    @Test
    void shouldFindUserById() {
        //given
        Long id = 1L;
        when(userRepository.getById(id)).thenReturn(null);

        //when
        userService.getUserById(id);

        //then
        //assertEquals("1L",)

        verify(userRepository, Mockito.times(1)).getById(id);
    }


    @Test
    void shouldUserBan() {
        //given
        User user = new User();
        user.setActive(true);
        Long id = 1L;
        when(userRepository.getById(id)).thenReturn(this.user);

        //when
        userService.userBan(id);

        //then
        assertTrue(user.isActive());

        verify(userRepository, Mockito.times(1)).getById(id);
    }


   /* @Test
    void shouldChangeUserRoles() {
        //given
        when(userRepository.getById())

        Map<String, String> form;
        Set<String> role = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        //when
        user.getRoles().clear();

        //then

    }*/

    @Test
    void shouldGetUserByName() {
        //given
        User user = new User();
        user.setName("Name");

        //when
        userRepository.findByName(user.getName());

        //then
        userService.getUserByName(user.getName());
        assertEquals("Name", user.getName());
    }

    @Test
    void shouldDeleteUserById() {
        //given
        Long id = 1L;

        //when
        userService.deleteUserById(id);

        //then
        assertTrue();
        verify(userRepository, times(1)).deleteById(id);
    }
}