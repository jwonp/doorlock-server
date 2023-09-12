package com.ikiningyou.drserver.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "userdetail")
public class UserDetail implements UserDetails {

  @Id
  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @OneToMany(mappedBy = "userDetail",fetch = FetchType.EAGER)
  // @JoinTable(
  //   name = "user_authority", //조인테이블명
  //   joinColumns = @JoinColumn(name = "username"), //외래키
  //   inverseJoinColumns = @JoinColumn(name = "user") //반대 엔티티의 외래키
  // )
  private List<Authority> authorityList;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorityList
      .stream()
      .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
      .collect(Collectors.toList());
  }

  @OneToOne(mappedBy = "userDetail")
  private User user;

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
