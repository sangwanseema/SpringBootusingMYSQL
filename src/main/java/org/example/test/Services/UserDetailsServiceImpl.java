package org.example.test.Services;

import org.example.test.Respository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.example.test.entity.User user=userEntryRepository.findByUserName(username);
        if(user!=null){
           UserDetails userDetails= org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;


        }
        throw new UsernameNotFoundException("User not found");
    }
}