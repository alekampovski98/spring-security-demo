package io.intertec.securitydemo.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;

@Profile("basic")
@Getter
@RequiredArgsConstructor
public enum Permission {
  READ("user:read"),
  WRITE("user:write");
  private final String permission;
}
