package com.pikachu.purple.infrastructure.persistence.user.repository;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    @Query("SELECT u FROM UserJpaEntity u WHERE u.email = :email")
    Optional<UserJpaEntity> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserJpaEntity u WHERE u.email = :email AND u.socialLoginProvider = :socialLoginProvider")
    Optional<UserJpaEntity> findByEmailAndSocialLoginProvider(
        @Param("email") String email,
        @Param("socialLoginProvider") SocialLoginProvider socialLoginProvider
    );

    @Query("SELECT u From UserJpaEntity u WHERE u.nickname = :nickname")
    Optional<UserJpaEntity> findByNickname(@Param("nickname") String nickname);

    @Query("SELECT count(*) FROM UserJpaEntity")
    int countTotalUsers();

}
