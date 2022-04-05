package com.example.service;

import com.example.demoshop.model.User;
import com.example.demoshop.model.enums.Role;
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


import java.util.*;

import static com.example.demoshop.model.enums.Role.ROLE_ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService testSubject;

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
        boolean result = testSubject.createUser(this.user);

        //then
        assertNotNull(this.user);
        assertTrue(result);

        verify(passwordEncoder, Mockito.times(1)).encode("123");
        verify(userRepository, Mockito.times(1)).save(user);
    }


    @Test
    void shouldGetAllUsers() {
        //Given
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        //when
        List<User> getAllUsers = testSubject.getAllUsers();

        //then
        assertNotNull(getAllUsers);

        verify(userRepository, times(1)).findAll();
    }


    @Test
    void shouldFindUserById() {
        //given
        User user = new User();
        Long id = 1L;
        user.setId(id);
        when(userRepository.getById(id)).thenReturn(user);

        //when
        testSubject.getUserById(id);

        //then
        assertEquals(1L, id);

        verify(userRepository, Mockito.times(1)).getById(id);
    }


    @Test
    void shouldUserBan_WhenTrueThenFalse() {
        //given
        User user = new User();
        Long id = 1L;
        user.setId(id);
        user.setActive(false);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //when
        testSubject.userBan(id);

        //then
        assertNotNull(user);
        assertEquals(1L, id);
        assertTrue(user.isActive());


        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findById(id);
    }


    @Test
    void shouldUserBan_WhenFalse_ThenTrue() {
        //given
        User user = new User();
        Long id = 1L;
        user.setId(id);
        user.setActive(true);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //when
        testSubject.userBan(id);

        //then
        assertNotNull(user);
        assertEquals(1L, id);
        assertFalse(user.isActive());


        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findById(id);
    }


    @Test
    void shouldUserBan_WhenNoUser() {
        //given
        User user = new User();
        Long id = null;
        user.setId(id);
        user.setActive(false);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        //when
        testSubject.userBan(user.getId());

        //then
        assertNotNull(user);
        assertNull(user.getId());
        assertTrue(user.isActive());


        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findById(user.getId());
    }


    @Test
    void shouldChangeUserRoles() {
        //given
        User user = new User();
        String name = "name";
        user.setName(name);
        Role role = ROLE_ADMIN;
        Map<String, String> form = new HashMap<>();
        form.put(name, role.name());

        when(userRepository.save(user)).thenReturn(user);

        //when
        boolean result = user.getRoles().add(role);
        testSubject.changeUserRoles(user,form);

        //then
        assertTrue(result);

        verify(userRepository, times(1)).save(user);
    }


    @Test
    void shouldGetUserByName() {
        //given
        User user = new User();
        String name = "name";
        user.setName(name);
        when(userRepository.findByName(name)).thenReturn(user);

        //when
        testSubject.getUserByName(user.getName());

        //then
        assertEquals("name", user.getName());
        assertNotNull(user);

        verify(userRepository, times(1)).findByName(name);
    }


    @Test
    void shouldDeleteUserById() {
        //given
        User user = new User();
        Long id = 1L;
        user.setId(id);

        //when
        testSubject.deleteUserById(id);

        //then
        verify(userRepository, times(1)).deleteById(id);
    }


    @Test
    void shouldGetUserByLogin() {
        //given
        User user = new User();
        String login = "login";
        user.setLogin(login);
        when(userRepository.findByLogin(login)).thenReturn(user);

        //when
        testSubject.getUserByLogin(login);

        //then
        assertEquals("login", user.getLogin());

        verify(userRepository, times(1)).findByLogin(login);
    }
}