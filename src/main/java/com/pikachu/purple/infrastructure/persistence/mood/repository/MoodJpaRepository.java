package com.pikachu.purple.infrastructure.persistence.mood.repository;

import com.pikachu.purple.infrastructure.persistence.mood.entity.MoodJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

public interface MoodJpaRepository extends JpaRepository<MoodJpaEntity, String> {

    List<MoodJpaEntity> findAllByNameIn(List<String> moodNames);

}
