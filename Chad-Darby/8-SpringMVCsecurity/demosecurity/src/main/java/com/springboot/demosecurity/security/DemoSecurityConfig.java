package com.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    // Add support for JDBC
    // "DataSource" inject data source auto-configured by Spring Boot
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Tell Spring Security to use JDBC authentication with our data source
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Custom tables
        // Define query to retrieve a user by username (How to find users)
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select user_id, pw, active from members where user_id=?");

        // Define query to retrieve the authorities by username (How to find roles)
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    // Configure security of web path in app, login, logout, etc.
    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Inside of this method, restrict access based on the HTTP request
        // ".anyRequest" Any request to the app must be authenticated
        // ".formLogin" We're customizing the form login process
        // ".loginPage" Show our custom form at the request mapping
        // ".loginProcessingUrl" Login form should POST data to this URL for processing
        // ."permitAll" Allow everyone to see login page, no need to be logged in
        http.authorizeRequests(configurer ->
            configurer
                .requestMatchers("/").hasRole("EMPLOYEE")
                .requestMatchers("/leaders/**").hasRole("MANAGER")
                .requestMatchers("/systems/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated())
                .formLogin(form -> 
                    form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                        .logout(logout -> logout.permitAll())
                        .exceptionHandling(configurer -> 
                            configurer.accessDeniedPage("/access-denied"));

        return http.build();
    }

}

    /* 
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
            .username("john")
            .password("{noop}test123")
            .roles("EMPLOYEE")
            .build();

        UserDetails mary = User.builder()
            .username("mary")
            .password("{noop}test123")
            .roles("EMPLOYEE", "MANAGER")
            .build();
        
        UserDetails susan = User.builder()
            .username("susan")
            .password("{noop}test123")
            .roles("EMPLOYEE", "MANAGER", "ADMIN")
            .build();

            return new InMemoryUserDetailsManager(john, mary, susan);
        }
    */