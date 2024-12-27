package com.pikachu.purple.infrastructure.persistence.statistic.repository;

import com.pikachu.purple.infrastructure.persistence.statistic.entity.StarRatingStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.StarRatingStatisticId;
import com.pikachu.purple.infrastructure.persistence.statistic.vo.StarRatingStatisticCompositeKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StarRatingStatisticJpaRepository extends
    JpaRepository<StarRatingStatisticJpaEntity, StarRatingStatisticId> {

    @Query("select srs "
        + "from StarRatingStatisticJpaEntity srs "
        + "where 1=1"
        + " and srs.perfumeJpaEntity.id = :#{#compositeKey.perfumeId}"
        + " and srs.score = :#{#compositeKey.score}"
    )
    Optional<StarRatingStatisticJpaEntity> findByCompositeKey(
        StarRatingStatisticCompositeKey compositeKey);

    @Query("select srs "
        + "from StarRatingStatisticJpaEntity srs "
        + "where srs.perfumeJpaEntity.id = :perfumeId "
        + "order by srs.score asc")
    List<StarRatingStatisticJpaEntity> findAllByPerfumeId(
        Long perfumeId);

}