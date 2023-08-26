package com.ikiningyou.drserver.auth;

import com.ikiningyou.drserver.model.dao.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

  @Autowired
  private PrincipalDetailsService userDetailService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication)
    throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    PrincipalDetails user = userDetailService.loadUserByUsername(username);

    return checkPassword(user, password, bCryptPasswordEncoder);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(
        authentication
      );
  }

  private Authentication checkPassword(
    PrincipalDetails user,
    String rawPassword,
    PasswordEncoder encoder
  ) {
    if (encoder.matches(rawPassword, user.getPassword())) {
      return new UsernamePasswordAuthenticationToken(
        user.getUsername(),
        user.getPassword(),
        user.getAuthorities()
      );
    } else {
      throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }
  }
}
