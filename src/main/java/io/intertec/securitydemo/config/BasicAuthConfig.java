package io.intertec.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static io.intertec.securitydemo.model.enums.Permission.WRITE;
import static io.intertec.securitydemo.model.enums.Role.MENTEE;
import static io.intertec.securitydemo.model.enums.Role.MENTOR;
import static org.springframework.security.config.Customizer.withDefaults;

@Profile("basic")
@EnableWebSecurity
@Configuration
public class BasicAuthConfig {


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
     .headers(h -> h.frameOptions().disable())
     .authorizeHttpRequests(auth ->
     {
       auth.requestMatchers("/api/v1/user/home").permitAll();
       auth.requestMatchers(HttpMethod.POST, "/api/v1/user").permitAll();
       auth.requestMatchers(HttpMethod.GET, "/api/v1/user/mentor").hasRole(MENTOR.name());
       auth.requestMatchers(HttpMethod.GET, "/api/v1/user/mentee").hasRole(MENTEE.name());
       auth.requestMatchers(HttpMethod.GET, "/api/v1/user/multipleRoles").hasAnyRole(MENTEE.name(), MENTOR.name());
       auth.requestMatchers(HttpMethod.GET, "/api/v1/user/findAll", "/api/v1/user/**").hasRole(MENTOR.name());
       auth.requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAuthority(WRITE.getPermission());
       auth.requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasAuthority(WRITE.getPermission());

       auth.anyRequest().authenticated();
     })
     .httpBasic(withDefaults())
     .build();
  }
}
