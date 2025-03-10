package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.MoodJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodJpaRepository extends JpaRepository<MoodJpaEntity, String> {

    List<MoodJpaEntity> findAllByNameIn(List<String> moodNames);

}
