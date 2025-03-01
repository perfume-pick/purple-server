package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeAccordJpaEntity;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeAccordJpaRepository extends JpaRepository<PerfumeAccordJpaEntity, Long> {

    List<PerfumeAccordJpaEntity> findAllByPerfumeId(Long perfumeId);

    List<PerfumeAccordJpaEntity> findAllByPerfumeIdOrderByValueDesc(Long perfumeId, Limit limit);

}
