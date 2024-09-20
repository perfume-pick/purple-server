package com.pikachu.purple.infrastructure.persistence.statistic.repository;

import com.pikachu.purple.infrastructure.persistence.statistic.entity.EvaluationStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.EvaluationStatisticId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluationStatisticJpaRepository extends
    JpaRepository<EvaluationStatisticJpaEntity, EvaluationStatisticId> {

  @Query("select es "
      + "from EvaluationStatisticJpaEntity es "
      + "where es.statisticsDate = :today and es.perfumeJpaEntity.id = :perfumeId "
      + "order by es.fieldCode asc, es.votes desc")
  List<EvaluationStatisticJpaEntity> findAllByTodayAndPerfumeIdOrderByVotesDesc(String today, Long perfumeId);

}