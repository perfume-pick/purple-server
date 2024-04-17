package com.pikachu.purple.infrastructure.user.repository;

import com.pikachu.purple.infrastructure.user.adaptor.UserJpaAdaptor;
import com.pikachu.purple.infrastructure.user.entity.UserJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    @Query("SELECT u FROM UserJpaEntity u WHERE u.email = :email")
    Optional<UserJpaEntity> findByEmail(@Param("email") String email);
}
