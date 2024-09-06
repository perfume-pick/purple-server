package com.pikachu.purple.infrastructure.persistence.mood.repository;

import com.pikachu.purple.infrastructure.persistence.mood.entity.MoodJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodJpaRepository extends JpaRepository<MoodJpaEntity, String> {

}
