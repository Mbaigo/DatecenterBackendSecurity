package com.datecenter.BackendSecurity.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
@CrossOrigin("*")
public class AdminController {
    @GetMapping("/")
    public String helloAdminController(){
        return "Admin access leve";
    }
}
