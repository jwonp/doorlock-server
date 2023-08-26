package com.ikiningyou.drserver.config;

import com.ikiningyou.drserver.auth.AuthenticationProviderService;
import com.ikiningyou.drserver.auth.LoginFailureHandler;
import com.ikiningyou.drserver.auth.LoginSuccessHandler;
import com.ikiningyou.drserver.util.property.Endpoints;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebConfig {

  @Autowired
  Endpoints endpoints;

  @Autowired
  private AuthenticationProviderService authenticationProvider;

  @Autowired
  private LoginSuccessHandler loginSuccessHandler;

  @Autowired
  private LoginFailureHandler loginFailureHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    return http
      .authenticationProvider(authenticationProvider)
      .httpBasic(c -> {
        c.disable();
      })
      .authorizeHttpRequests(c -> {
        c
          .requestMatchers(
            HttpMethod.POST,
            "/auth/login",
            "/auth/login/success",
            "/auth/logout",
            "/auth/logout/success",
            "/user"
          )
          .permitAll()
          .anyRequest()
          .authenticated();
      })
      .csrf(c -> c.disable())
      .cors(c -> {
        CorsConfigurationSource source = request -> {
          CorsConfiguration config = new CorsConfiguration();

          // log.info("endpoint is {}", endpoints.getFrontend());
          config.setAllowedOrigins(List.of(endpoints.getFrontend()));
          config.setAllowedMethods(
            List.of("GET", "POST", "PUT", "PATCH", "DELETE")
          );
          config.setAllowedHeaders(List.of("X-IDENTIFIER"));
          return config;
        };
        c.configurationSource(source);
      })
      .formLogin(formLogin -> {
        formLogin
          .loginProcessingUrl("/auth/login")
          .usernameParameter("username")
          .passwordParameter("password")
          // .defaultSuccessUrl("/auth/login/success")
          .successHandler(loginSuccessHandler)
          .failureHandler(loginFailureHandler);
      })
      .logout(c ->
        c
          .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
          .logoutSuccessHandler((request, response, authentication) -> {
            response.sendRedirect("/auth/logout/success");
            return;
          })
      )
      .sessionManagement(c ->
        c
          .sessionFixation()
          .migrateSession()
          .maximumSessions(5)
          .maxSessionsPreventsLogin(true)
      )
      .build();
  }
}
