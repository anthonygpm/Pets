package dev.anthonygpm.pets.repository;

import dev.anthonygpm.pets.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPhone(String phone);
}
