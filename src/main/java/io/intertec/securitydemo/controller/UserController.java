package io.intertec.securitydemo.controller;

import io.intertec.securitydemo.dto.UserDto;
import io.intertec.securitydemo.model.User;
import io.intertec.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("basic")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/home")
  public String home() {
    return "Hello Intertec.io People";
  }

  @GetMapping("/mentee")
  public String user() {
    return "Hello mentee!";
  }

  @GetMapping("/mentor")
  public String admin() {
    return "Hello mentor!";
  }

  @GetMapping("/multipleRoles")
  public String multipleRoles() {
    return "THIS IS ACCESSIBLE FOR MULTIPLE ROLES!";
  }

  @GetMapping("/{username}")
  private User findUser(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @GetMapping("/findAll")
  private List<User> findAllUsers() {
    return userService.findAll();
  }

  @PostMapping
  private User saveUser(@RequestBody UserDto userDto) {
    return userService.saveUser(userDto);
  }

  @PutMapping("/{username}")
  private User editUser(@RequestBody UserDto userDto) {
    return userService.editUser(userDto);
  }

  @DeleteMapping("/{username}")
  private User deleteUser(@PathVariable String username) {
    return userService.deleteUser(username);
  }
}
