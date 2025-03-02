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

    List<StarRatingStatisticJpaEntity> findAllByPerfumeIdOrderByScoreAsc(Long perfumeId);

    List<StarRatingStatisticJpaEntity> findAllByPerfumeIdInOrderByScoreAsc(List<Long> perfumeIds);

}