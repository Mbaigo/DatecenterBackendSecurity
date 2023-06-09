package com.datecenter.BackendSecurity.securityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//a partir de spring security 6
@Configuration
public class SecurityConfiguration {
    @Bean
    //algorithme de cryptage du password
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean //gestionnaire d'authentification
    public AuthenticationManager authenticationManager(
            // fournisseur des informations d'authentifications de l'utilisateur
            UserDetailsService detailsService){
        /* instanciation d'un fournisseur d'authentification */
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        /* fournisseur d'authentification utilisant un UserDetailsService pour obtenir les informations
        d'identification de l'utilisateur et effectuer la vérification de l'authentification.*/
        daoAuthenticationProvider.setUserDetailsService(detailsService);
        /*gestionnaire d'authentification qui contient un  fournisseur d'authentification en l'occurence
        daoAuthenticationProvider. Il peut contenir plusieurs fournisseurs d'authenficiation.
        Il gère la sélection et la validation du fournisseur approprié lors de l'authentification.  */
        return  new ProviderManager(daoAuthenticationProvider);
    }
    @Bean
    //le filtre des endpoints permis et non permis
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return  http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .httpBasic().and()
                .build();
    }
}
