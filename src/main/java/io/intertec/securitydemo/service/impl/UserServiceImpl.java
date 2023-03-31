package io.intertec.securitydemo.service.impl;

import io.intertec.securitydemo.dto.UserDto;
import io.intertec.securitydemo.model.User;
import io.intertec.securitydemo.repository.UserRepository;
import io.intertec.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("basic")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User findByUsername(String username) {
    return userRepository.findById(username)
     .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
  }

  @Override
  public User saveUser(UserDto userDto) {
    User user = User.builder()
      .username(userDto.getUsername())
      .password(encodePassword(userDto.getPassword()))
      .role(userDto.getRole())
      .build();
    return saveUser(user);
  }

  @Override
  public User editUser(UserDto userDto) {
    User user = findByUsername(userDto.getUsername());
    user.setPassword(encodePassword(userDto.getPassword()));
    user.setRole(userDto.getRole());
    return saveUser(user);
  }

  @Override
  public User deleteUser(String username) {
    User user = findByUsername(username);
    userRepository.delete(user);
    return user;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  private User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return findByUsername(username);
  }

  private String encodePassword(String plainTextPassword) {
    return passwordEncoder.encode(plainTextPassword);
  }
}
