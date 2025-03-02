package com.pikachu.purple.infrastructure.persistence.statistic.repository;

import com.pikachu.purple.infrastructure.persistence.statistic.entity.EvaluationStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.EvaluationStatisticId;
import com.pikachu.purple.infrastructure.persistence.statistic.vo.EvaluationStatisticCompositeKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluationStatisticJpaRepository extends
    JpaRepository<EvaluationStatisticJpaEntity, EvaluationStatisticId> {

  List<EvaluationStatisticJpaEntity> findAllByPerfumeIdOrderByFieldCodeAscVotesDesc(Long perfumeId);

}