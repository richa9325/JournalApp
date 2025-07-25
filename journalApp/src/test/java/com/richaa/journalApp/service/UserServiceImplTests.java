package com.richaa.journalApp.service;

import com.richaa.journalApp.entity.User;
import com.richaa.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static  org.mockito.Mockito.*;

@ActiveProfiles("dev")
public class UserServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

@Test
    void loadUserByUsernameTest() {
    when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Richa").password("inrinrick").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Richa");
    Assertions.assertNotNull(user);
    }

}
