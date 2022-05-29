package com.famly.services;

import com.famly.entity.User;
import com.famly.entity.FamlyUserDetails;
import com.famly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    /**
     * TODO : update with correct user. Fetch from UserService
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = new User();
        try {
            user = userRepository.getUserByemail(email);
        } catch(Exception e){
            System.out.println("DEBUG - Load User "+ e.getMessage());
        }
        System.out.println(user);
        return new FamlyUserDetails(user);
    }
}