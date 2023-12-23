package org.vinio.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.vinio.models.enums.Role;
import org.vinio.repositories.UserRepository;
import org.vinio.services.imlementations.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {
    private UserRepository userRepository;
//
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, SecurityContextRepository securityContextRepository)
            throws Exception {
        httpSecurity
                .authorizeRequests( // авторизация запроса - настройка правил доступа
                        authorizeRequests ->
                                authorizeRequests.
                                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        // запросы, доступные всем
                                        .requestMatchers("/main", "/users/login", "/users/register",
                                                "/brands/getAll", "/models/getAll", "/offers/getAll",
                                                "/models/getModelsByCategory/{category}", "/models/getModelsByBrand/{id}",
                                                "offers/getByModel/{id}").permitAll().
                                        requestMatchers("/users/profile", "/offers/add").hasRole(Role.User.name()).
                                        requestMatchers("/models/add", "/brands/add").hasRole(Role.Admin.name())
                                        // запросы, доступные только авторизованным пользователям
                                        .anyRequest().authenticated()
                )
                .formLogin( // форма авторизации?
                        formLogin ->
                                formLogin.loginPage("/users/login").
                                        usernameParameter(UsernamePasswordAuthenticationFilter
                                                .SPRING_SECURITY_FORM_USERNAME_KEY).
                                        passwordParameter(UsernamePasswordAuthenticationFilter
                                                .SPRING_SECURITY_FORM_PASSWORD_KEY).
                                        defaultSuccessUrl("/main").
                                        failureForwardUrl("/users/login-error")
                )
                .logout((logout) ->
                        logout.logoutUrl("/users/logout"). // вызов выхода из системы
                                logoutSuccessUrl("/main"). // перенаправления после успешного выхода
                                invalidateHttpSession(true)
                ).securityContext(
                        securityContext -> securityContext.
                                securityContextRepository(securityContextRepository)
                );

        return httpSecurity.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() { return new UserDetailsServiceImpl(userRepository); }
}
