package com.datecenter.BackendSecurity.securityConfiguration;

import com.datecenter.BackendSecurity.jwtUtils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

//a partir de spring security 6
@Configuration @EnableWebSecurity
public class SecurityConfiguration {
    //injection de l'algo de cryptage RSA
    RSAKeyProperties keyProperties;
// injection par constructor
    public SecurityConfiguration(RSAKeyProperties keyProperties) {
        this.keyProperties = keyProperties;
    }

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
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    //algo de décodage
    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keyProperties.getRsaPublicKey()).build();
    }

    //Encodage de la clé
    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk= new RSAKey.Builder(keyProperties.getRsaPublicKey())
                                         .privateKey(keyProperties.getRsaPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        NimbusJwtEncoder nimbusJwtEncoder = new NimbusJwtEncoder(jwks);
        return nimbusJwtEncoder;
    }
}
