package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.NoteJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteJpaRepository extends JpaRepository<NoteJpaEntity, Long> {

    @Query("select n "
        + "from NoteJpaEntity n "
        + "where n.perfumeJpaEntity.id = :perfumeId")
    List<NoteJpaEntity> findAllByPerfumeId(Long perfumeId);

}
