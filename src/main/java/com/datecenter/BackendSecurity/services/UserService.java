package com.datecenter.BackendSecurity.services;

import com.datecenter.BackendSecurity.models.Role;
import com.datecenter.BackendSecurity.models.UserApplication;
import com.datecenter.BackendSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    //Charger l'utilisateur avec son username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Nous sommes de le service user");
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Le username n'existe pas"));
    }
}
