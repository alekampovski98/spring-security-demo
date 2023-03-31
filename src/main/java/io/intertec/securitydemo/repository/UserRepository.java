package io.intertec.securitydemo.repository;

import io.intertec.securitydemo.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("basic")
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
