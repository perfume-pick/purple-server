package com.pikachu.purple.infrastructure.persistence.userPreferenceNote.repository;

import com.pikachu.purple.infrastructure.persistence.userPreferenceNote.entity.UserPreferenceNoteJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceNoteJpaRepository extends JpaRepository<UserPreferenceNoteJpaEntity, Long> {

    @Query("SELECT upn FROM UserPreferenceNoteJpaEntity upn WHERE upn.userId = :userId")
    List<UserPreferenceNoteJpaEntity> findByUserId(@Param("userId") Long userId);

}
