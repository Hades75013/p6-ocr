package com.sif.p6.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String adminRole = RoleEnum.ADMIN.name();

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity https) throws Exception {
        https.authorizeRequests()
                .antMatchers("/user**").authenticated()
                .antMatchers("/admin**").hasAuthority(adminRole)
                .anyRequest().permitAll()
            .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/admin/espaceperso").failureUrl("/connection")
                .usernameParameter("username").passwordParameter("password")
            .and()
                .logout().invalidateHttpSession(true)
                .logoutUrl("/deconnection")
                .logoutSuccessUrl("/formconnection")
            .and()
                .csrf()
            .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/formconnection");
    }
}