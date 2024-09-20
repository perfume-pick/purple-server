package com.pikachu.purple.infrastructure.persistence.statistic.repository;

import com.pikachu.purple.infrastructure.persistence.statistic.entity.StarRatingStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.StarRatingStatisticId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StarRatingStatisticJpaRepository extends
    JpaRepository<StarRatingStatisticJpaEntity, StarRatingStatisticId> {

    @Query("select srs "
        + "from StarRatingStatisticJpaEntity srs "
        + "where srs.statisticsDate = :today and srs.perfumeJpaEntity.id = :perfumeId")
    List<StarRatingStatisticJpaEntity> findAllByTodayAndPerfumeId(String today, Long perfumeId);

}