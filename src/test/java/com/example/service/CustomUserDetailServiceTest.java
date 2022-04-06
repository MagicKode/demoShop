package com.example.service;

import com.example.demoshop.model.User;
import com.example.demoshop.repository.UserRepository;
import com.example.demoshop.service.CustomUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailService testSubject;

    User user;

    @BeforeEach
    void init(){
        user = new User();
        user.setLogin("login");
    }


    @Test
    void shouldLoadUserByUsername() {
        //given
        User user = new User();
        String login = "login";
        user.setLogin(login);
        when(userRepository.findByLogin(user.getLogin())).thenReturn(user);

        //when
        testSubject.loadUserByUsername(login);

        //then
        assertEquals("login", login);

        verify(userRepository, times(1)).findByLogin(login);
    }
}
