package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.User;
import com.doganmehmet.app.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByRole(Role role);
}
