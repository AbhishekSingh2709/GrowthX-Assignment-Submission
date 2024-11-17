package com.assignmentSubmission.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class Security
{
    private JwtConfig jc;

    public Security(JwtConfig jc) {
        this.jc = jc;
    }

    // Add your security configurations here
    // Example: Enable HTTPS, configure authentication, etc.
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable().csrf().disable();
        http.addFilterBefore(jc, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/users-registration/userInfo" , "/api/v1/users-registration/userLogin", "/api/v1/Admin/adminRegister","/api/v1/Admin/adminLogin")
                .permitAll()
                .requestMatchers("/api/v1/users-registration/submittingAssignment","http://localhost:8080/api/v1/users-registration/fetchAdmins", "/api/v1/users-registration/fetchAdmins")
                .hasRole("USER")
                .requestMatchers("/api/v1/Admin/fetchAssignments","/api/v1/Admin/updateStatusAccept","/api/v1/Admin/updateStatusRejected")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
        return http.build();
    }
}
