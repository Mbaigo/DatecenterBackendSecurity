package com.datecenter.BackendSecurity.services;

import com.datecenter.BackendSecurity.models.Role;
import com.datecenter.BackendSecurity.models.UserApplication;
import com.datecenter.BackendSecurity.repository.RoleRepository;
import com.datecenter.BackendSecurity.repository.UserRepository;
import jakarta.persistence.SecondaryTable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service @Transactional
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //creation d'un user
    public UserApplication registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return  userRepository.save(new UserApplication(0,username,encodedPassword, authorities));
    }
}
