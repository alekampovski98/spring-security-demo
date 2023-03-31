package io.intertec.securitydemo.dto;

import io.intertec.securitydemo.model.enums.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;

@Profile("basic")
@Data
@RequiredArgsConstructor
public class UserDto {
  private final String username;
  private final String password;
  private final Role role;
}
