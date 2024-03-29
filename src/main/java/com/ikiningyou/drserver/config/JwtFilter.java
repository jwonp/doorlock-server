package com.ikiningyou.drserver.config;

import com.ikiningyou.drserver.service.AuthService;
import com.ikiningyou.drserver.util.Authorities;
import com.ikiningyou.drserver.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  private final AuthService authService;
  private final String secretKey;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    log.info("authorization : {}", authorization);

    if (authorization == null || !authorization.startsWith("Bearer ")) {
      log.error("authorization 없습니다.");
      filterChain.doFilter(request, response);
      return;
    }

    //Token 꺼내기
    String token = authorization.split(" ")[1];
    log.info("token is {}", token);
    if (JwtUtil.isTokenNotVaild(token, secretKey)) {
      log.error("Token is Not vaild");
      filterChain.doFilter(request, response);
      return;
    }

    //is Token Expired
    if (JwtUtil.isExpired(token, secretKey)) {
      log.error("Token is expired");
      filterChain.doFilter(request, response);
      return;
    }

    //user id Token에서 꺼내기
    String userId = JwtUtil.getUserId(token, secretKey);

    log.info("username:{}", userId);
    boolean isAdmin = authService.isAdmin(userId);
    String authority = Authorities.USER;
    if (isAdmin) {
      authority = Authorities.ADMIN;
    }
    log.info("is admin {} , authority {} ", isAdmin, authority);
    //권한 부여
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
      userId,
      null,
      List.of(new SimpleGrantedAuthority(authority))
    );

    //Detail
    authenticationToken.setDetails(
      new WebAuthenticationDetailsSource().buildDetails(request)
    );
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);
  }
}
