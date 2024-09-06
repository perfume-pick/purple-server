package com.pikachu.purple.infrastructure.persistence.usermood.repository;

import com.pikachu.purple.infrastructure.persistence.usermood.entity.UserMoodJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMoodJpaRepository extends JpaRepository<UserMoodJpaEntity, Long> {

}
