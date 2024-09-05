package com.pikachu.purple.infrastructure.persistence.evaluation.repository;

import com.pikachu.purple.infrastructure.persistence.evaluation.entity.FragranticaEvaluationJpaEntity;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FragranticaEvaluationJpaRepository extends
    JpaRepository<FragranticaEvaluationJpaEntity, Long> {

  List<FragranticaEvaluationJpaEntity> findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
      Long perfumeId,
      String fieldCode
  );

}