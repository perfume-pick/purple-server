package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeNoteJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeNoteJpaRepository extends JpaRepository<PerfumeNoteJpaEntity, Long> {

    List<PerfumeNoteJpaEntity> findByPerfumeIdIn(List<Long> perfumeIdList);

    List<PerfumeNoteJpaEntity> findAllByPerfumeId(Long perfumeId);

}
