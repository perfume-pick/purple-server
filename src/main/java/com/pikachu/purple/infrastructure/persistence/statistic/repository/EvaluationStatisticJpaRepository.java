package com.pikachu.purple.infrastructure.persistence.statistic.repository;

import com.pikachu.purple.infrastructure.persistence.statistic.entity.EvaluationStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.EvaluationStatisticId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationStatisticJpaRepository extends
    JpaRepository<EvaluationStatisticJpaEntity, EvaluationStatisticId> {

  List<EvaluationStatisticJpaEntity> findAllByPerfumeIdOrderByFieldCodeAscVotesDesc(Long perfumeId);

}