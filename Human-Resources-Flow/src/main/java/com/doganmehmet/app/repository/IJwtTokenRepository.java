package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.JwtToken;
import com.doganmehmet.app.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByUser(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM JwtToken j WHERE j.user = :user")
    void deleteByUser(User user);
}
