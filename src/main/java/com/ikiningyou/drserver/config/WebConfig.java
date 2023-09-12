package com.ikiningyou.drserver.config;

import com.ikiningyou.drserver.service.AuthService;
import com.ikiningyou.drserver.util.Authorities;
import com.ikiningyou.drserver.util.property.Endpoints;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebConfig {

  @Autowired
  Endpoints endpoints;

  @Autowired
  private AuthService authService;

  @Value("${jwt.token.secret}")
  private String secretKey;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    return http
      .httpBasic(c -> {
        c.disable();
      })
      .csrf(c -> c.disable())
      // .cors(c -> c.disable())
      .cors(c -> {
        CorsConfigurationSource source = request -> {
          CorsConfiguration config = new CorsConfiguration();

          log.info(
            "endpoint is {} Authorization is {}",
            endpoints.getFrontend(),
            request.getHeader("Authorization")
          );
          config.setAllowCredentials(true);
          config.setAllowedOrigins(
            List.of("http://127.0.0.1:3000", "http://localhost:3000")
          );
          config.setAllowedMethods(
            List.of("GET", "POST", "PUT", "PATCH", "DELETE")
          );
          config.setAllowedHeaders(List.of("Authorization"));
          return config;
        };
        c.configurationSource(source);
      })
      .sessionManagement(c ->
        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authorizeHttpRequests(c -> {
        c
          .requestMatchers(
            HttpMethod.POST,
            "/auth/login",
            "/auth/logout",
            "/user",
            "/card/lost",
            "/auth/card",
            "/auth/token/valid"
          )
          .permitAll()
          .requestMatchers(
            "/card/index",
            "/room/select",
            "/reservation/request"
          )
          .hasAuthority(Authorities.USER)
          .requestMatchers(HttpMethod.GET, "/reservation")
          .hasAuthority(Authorities.USER)
          .requestMatchers(
            "/auth/admin",
            "/card",
            "/card/admin/lost",
            "/card/admin",
            "/card/list",
            "/card/list/reservation",
            "/card/search",
            "/card/lost/list",
            "/user/admin",
            "/user/list",
            "/user/list/reservation",
            "/user/search",
            "/room",
            "/room/admin",
            "/room/list",
            "/room/list/reservation",
            "/room/search",
            "/reservation/admin/reserved",
            "/reservation/admin",
            "/reservation/list",
            "/reservation/list/user",
            "/reservation/list/full",
            "/reservation/full",
            "/log/admin/unauthorized",
            "/log/admin",
            "/log/list"
          )
          .hasAuthority(Authorities.ADMIN)
          .requestMatchers(HttpMethod.PATCH, "/reservation")
          .hasAuthority(Authorities.ADMIN)
          .requestMatchers(HttpMethod.DELETE, "/reservation")
          .hasAuthority(Authorities.ADMIN)
          .requestMatchers(HttpMethod.POST, "/reservation")
          .hasAuthority(Authorities.ADMIN)
          .anyRequest()
          .authenticated();
      })
      .addFilterBefore(
        new JwtFilter(authService, secretKey),
        UsernamePasswordAuthenticationFilter.class
      )
      .build();
  }
}
