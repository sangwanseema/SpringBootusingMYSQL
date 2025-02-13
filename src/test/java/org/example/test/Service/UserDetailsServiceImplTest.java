package org.example.test.Service;

import org.example.test.Respository.UserEntryRepository;
import org.example.test.Services.UserDetailsServiceImpl;
import org.example.test.Services.UserService;
import org.example.test.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

//@SpringBootTest
public class UserDetailsServiceImplTest {
    //@Autowired
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Mock
    private UserEntryRepository userEntryRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsernameTes()
    {
        when(userEntryRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Kanha").password("Kanha").roles(new ArrayList<>()).build());
   UserDetails user= userDetailsServiceImpl.loadUserByUsername("seemasangwan");
       Assertions.assertNotNull(user);
    }
}
