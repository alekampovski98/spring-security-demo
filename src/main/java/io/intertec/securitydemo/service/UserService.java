package io.intertec.securitydemo.service;

import io.intertec.securitydemo.dto.UserDto;
import io.intertec.securitydemo.model.User;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("basic")
public interface UserService {
  User saveUser(UserDto userDto);
  User editUser(UserDto userDto);
  User deleteUser(String username);

  User findByUsername(String username);
  List<User> findAll();
}
