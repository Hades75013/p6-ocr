package com.sif.p6.security;

import javax.sql.DataSource;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

     @Autowired
     BCryptPasswordEncoder bCryptPasswordEncoder;

     @Autowired
     private DataSource dataSource;

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {

         BCryptPasswordEncoder bcpe=getBCPE();

         auth.jdbcAuthentication()

         .dataSource(dataSource)

         .usersByUsernameQuery("select pseudo as principal, mot_de_passe as credentials, active from utilisateur where pseudo=? and active=1")

         .authoritiesByUsernameQuery("SELECT utilisateur.pseudo as principal, "

                   + "role.nom as role from role, utilisateur, utilisateur_role"

                   + "  WHERE utilisateur_role.utilisateur_id = utilisateur.id AND role.id = utilisateur_role.role_id AND utilisateur.pseudo=?")

         .rolePrefix("ROLE_")

         .passwordEncoder(bcpe);

     }
      
     
     protected void configure(HttpSecurity https ) throws Exception{

 		https.formLogin();

 		https.authorizeRequests().antMatchers("/User/*").hasRole("USER");

 		https.authorizeRequests().antMatchers("/User/*, /Admin/*").hasRole("ADMIN");

 		https.exceptionHandling().accessDeniedPage("/403");

 	}
     
     @Bean
     BCryptPasswordEncoder getBCPE() {

         return new BCryptPasswordEncoder();
     }

}