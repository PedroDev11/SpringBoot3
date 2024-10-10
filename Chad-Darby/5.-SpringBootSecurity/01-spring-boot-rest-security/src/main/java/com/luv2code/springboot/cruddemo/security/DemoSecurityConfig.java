package com.luv2code.springboot.cruddemo.security;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/* Since we defined our users here... Spring Boot won't use the user/password
from the application.properties file. Instaed, it'll use this user details manager that we
just created */
@Configuration
public class DemoSecurityConfig {
    // Add support for JDBC Authentication instead of use hardcode for users
    // USING CUSTOM TABLES
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        /* Hey this is how you find my custom tables, this is the query that you should use
        and these are the column names that you should use */
        // Define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select user_id, pw, active from members where user_id=?"
        );
        
        // Define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    /* Restricting access based on roles */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Setting authorization roles
        http.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // Use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        /* Disable Cross Site Request Forgery (CSRF)
        In general, not requiered for stateless REST APIs use POST, PUT, DELETE and/or PATCH */
        http.csrf(csrf -> csrf.disable());

        // return that given information
        return http.build();
    }
}

/* @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        // Creating users
        UserDetails john = User.builder()
            .username("john")
            .password("{noop}test123")
            .roles("EMPLOYEE")
            .build();

        UserDetails mary = User.builder()
            .username("mary")
            .password("{noop}test123")
            .roles("EMPLOYEE, MANAGER")
            .build();

        UserDetails susan = User.builder()
            .username("susan")
            .password("{noop}test123")
            .roles("EMPLOYEE, MANAGER, ADMIN")
            .build();

        // Return an instance of this memory user details manager
        return new InMemoryUserDetailsManager(john, mary, susan);
    } */

    // USING THE DEFAULT SPRING SECURITY TABLES SCHEMA (users, authorities)
    // Inject data source auto configured by Spring Boot
    /*
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        Tells Spring Security to use JDBC authentication with our data source. Spring
        Security will look in a table called Users, all the information is stored in the DB.
        Use the exact same table names and th exact same column names, required
        return new JdbcUserDetailsManager(dataSource);
    } */