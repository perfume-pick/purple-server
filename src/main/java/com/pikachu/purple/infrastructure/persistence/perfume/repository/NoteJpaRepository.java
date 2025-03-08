package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.NoteJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteJpaRepository extends JpaRepository<NoteJpaEntity, Long> {

    List<NoteJpaEntity> findAllByPerfumeId(Long perfumeId);

}
