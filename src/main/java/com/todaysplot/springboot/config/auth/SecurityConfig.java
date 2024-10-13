package com.todaysplot.springboot.config.auth;

import com.todaysplot.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // H2-console 사용을 위해 Frame 옵션 비활성화
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "h2-console/**").permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated()
                );
        http.logout(logout-> logout
                .logoutSuccessUrl("/")
                );
        http.oauth2Login(oauth2Login -> oauth2Login
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService) // 사용자 정보 엔드포인트를 처리하는 사용자 서비스 설정
                )
        );
        return http.build();
    }

}
