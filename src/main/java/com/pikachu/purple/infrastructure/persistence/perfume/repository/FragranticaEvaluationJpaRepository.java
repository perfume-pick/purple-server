package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.FragranticaEvaluationJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranticaEvaluationJpaRepository extends
    JpaRepository<FragranticaEvaluationJpaEntity, Long> {

    List<FragranticaEvaluationJpaEntity> findByPerfumeIdOrderByFieldCodeAscVotesDescOptionCodeAsc(Long perfumeId);

}
