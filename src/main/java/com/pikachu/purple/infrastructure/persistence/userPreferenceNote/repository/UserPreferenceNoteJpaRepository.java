package com.pikachu.purple.infrastructure.persistence.userPreferenceNote.repository;

import com.pikachu.purple.infrastructure.persistence.userPreferenceNote.entity.UserPreferenceNoteJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceNoteJpaRepository extends JpaRepository<UserPreferenceNoteJpaEntity, Long> {

    List<UserPreferenceNoteJpaEntity> findByUserId(Long userId);

}
