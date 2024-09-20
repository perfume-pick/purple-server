package com.pikachu.purple.infrastructure.persistence.statistic.adaptor;

import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.persistence.review.repository.StarRatingJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.EvaluationStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.StarRatingStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.repository.StarRatingStatisticJpaRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarRatingStatisticJpaAdaptor implements StarRatingStatisticRepository {

    private final StarRatingStatisticJpaRepository starRatingStatisticJpaRepository;

    @Override
    public List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId) {
        Instant today = Instant.now();
        ZonedDateTime zonedDateTime = today.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = zonedDateTime.format(formatter);

        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities =
            starRatingStatisticJpaRepository.findAllByTodayAndPerfumeId(
                formattedDate,
                perfumeId
            );

        return starRatingStatisticJpaEntities.stream()
            .map(StarRatingStatisticJpaEntity::toDomain)
            .toList();
    }

}
