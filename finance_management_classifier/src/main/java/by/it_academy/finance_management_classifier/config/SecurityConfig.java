package by.it_academy.finance_management_classifier.config;

import by.it_academy.finance_management_classifier.controller.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception  {

        http = http.cors().and().csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                )
                .accessDeniedHandler((request, response, ex) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                })
                .and();

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.GET,"/currency","/category").permitAll()
                .requestMatchers(HttpMethod.GET,"/currency/{uuid}","/category/{uuid}").authenticated()

                .requestMatchers(HttpMethod.POST,"/currency","/category").hasAnyRole("ADMIN","MANAGER")

                .anyRequest().authenticated()
        );

        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}