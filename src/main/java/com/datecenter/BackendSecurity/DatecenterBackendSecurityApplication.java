package com.datecenter.BackendSecurity;

import com.datecenter.BackendSecurity.models.Role;
import com.datecenter.BackendSecurity.models.UserApplication;
import com.datecenter.BackendSecurity.repository.RoleRepository;
import com.datecenter.BackendSecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DatecenterBackendSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatecenterBackendSecurityApplication.class, args);
	}
	/*@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder){
		return  args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole=roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			UserApplication admin = new UserApplication(1, "admin", encoder.encode("abbay"), roles);
			userRepository.save(admin);
		};
	}*/

}
