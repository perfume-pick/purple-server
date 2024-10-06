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

  @Query("select es "
      + "from EvaluationStatisticJpaEntity es "
      + "where es.statisticsDate = :#{#compositeKey.statisticsDate}"
      + " and es.perfumeJpaEntity.id = :#{#compositeKey.perfumeId}"
      + " and es.fieldCode = :#{#compositeKey.fieldCode}"
      + " and es.optionCode = :#{#compositeKey.optionCode}")
  Optional<EvaluationStatisticJpaEntity> findByCompositeKey(
      EvaluationStatisticCompositeKey compositeKey);

  @Query("select es "
      + "from EvaluationStatisticJpaEntity es "
      + "where es.statisticsDate = :today and es.perfumeJpaEntity.id = :perfumeId "
      + "order by es.fieldCode asc, es.votes desc")
  List<EvaluationStatisticJpaEntity> findAllByStatisticsDateAndPerfumeIdOrderByVotesDesc(String today, Long perfumeId);

  @Query("select es "
      + "from EvaluationStatisticJpaEntity es "
      + "where es.statisticsDate = :statisticsDate "
      + "order by es.perfumeJpaEntity.id asc, es.fieldCode asc, es.optionCode asc")
  List<EvaluationStatisticJpaEntity> findAllByStatisticsDate(String statisticsDate);

}