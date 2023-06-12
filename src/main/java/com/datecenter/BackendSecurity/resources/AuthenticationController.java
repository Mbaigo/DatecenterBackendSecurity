package com.datecenter.BackendSecurity.resources;

import com.datecenter.BackendSecurity.models.LoginResponseDTO;
import com.datecenter.BackendSecurity.models.UserApplication;
import com.datecenter.BackendSecurity.models.UserRegistrationDTO;
import com.datecenter.BackendSecurity.services.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth") @CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public UserApplication register(@RequestBody UserRegistrationDTO userRegistrationDTO){
        return authenticationService.registerUser(userRegistrationDTO.getUsername(), userRegistrationDTO.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody UserRegistrationDTO registrationDTO){
        return  authenticationService.loginUser(registrationDTO.getUsername(), registrationDTO.getPassword());
    }
}
