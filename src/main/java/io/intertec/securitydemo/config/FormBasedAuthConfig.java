package io.intertec.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

@Profile("form")
@Configuration
@EnableWebSecurity
public class FormBasedAuthConfig {

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    UserDetails user = User.withDefaultPasswordEncoder()
     .username("user")
     .password("user")
     .roles("USER")
     .build();

    UserDetails admin = User.withDefaultPasswordEncoder()
     .username("admin")
     .password("admin")
     .roles("ADMIN")
     .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
     .authorizeHttpRequests(auth -> {
       auth.requestMatchers("/", "index", "/css/*", "/js/*").permitAll();
       auth.anyRequest().authenticated();
     })
     .formLogin(login -> {
       login.loginPage("/login").permitAll();
       login.defaultSuccessUrl("/success", true);
     })
     .rememberMe(config -> {
       config.tokenValiditySeconds((int) TimeUnit.DAYS.toMillis(30));
       config.key("someSecuredKeyHere");
       config.rememberMeParameter("remember-me");
     })
     .logout(logout -> {
       logout.clearAuthentication(true);
       logout.invalidateHttpSession(true);
       logout.deleteCookies("JSESSIONID", "remember-me");
     })
     .build();
  }
}
