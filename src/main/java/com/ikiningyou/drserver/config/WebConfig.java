package com.ikiningyou.drserver.config;

import com.ikiningyou.drserver.util.property.Endpoints;
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

@Configuration
@EnableWebSecurity
public class WebConfig {

  @Autowired
  Endpoints endpoints;

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
      .cors(c -> c.disable()) // .cors(c -> {
      //   CorsConfigurationSource source = request -> {
      //     CorsConfiguration config = new CorsConfiguration();

      //     // log.info("endpoint is {}", endpoints.getFrontend());
      //     config.setAllowedOrigins(List.of(endpoints.getFrontend()));
      //     config.setAllowedMethods(
      //       List.of("GET", "POST", "PUT", "PATCH", "DELETE")
      //     );
      //     config.setAllowedHeaders(List.of("X-IDENTIFIER"));
      //     return config;
      //   };
      //   c.configurationSource(source);
      // })
      .sessionManagement(c ->
        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authorizeHttpRequests(c -> {
        c
          .requestMatchers(
            HttpMethod.POST,
            "/auth/login",
            "/auth/logout",
            "/auth/logout/success",
            "/user"
          )
          .permitAll()
          .anyRequest()
          .authenticated();
      })
      .addFilterBefore(
        new JwtFilter(secretKey),
        UsernamePasswordAuthenticationFilter.class
      )
      .build();
  }
}
