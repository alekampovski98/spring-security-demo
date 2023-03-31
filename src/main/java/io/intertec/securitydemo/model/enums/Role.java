package io.intertec.securitydemo.model.enums;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static io.intertec.securitydemo.model.enums.Permission.*;

@Profile("basic")
@Getter
@RequiredArgsConstructor
public enum Role {
  MENTOR(Sets.newHashSet(READ, WRITE)),
  MENTEE(Sets.newHashSet(READ)),
  GUEST(Sets.newHashSet());

  private final Set<Permission> permissions;

  public Set<SimpleGrantedAuthority> getAuthorities() {
    Set<SimpleGrantedAuthority> authorities = getPermissions()
     .stream().map(p -> new SimpleGrantedAuthority(p.getPermission()))
     .collect(Collectors.toSet());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
