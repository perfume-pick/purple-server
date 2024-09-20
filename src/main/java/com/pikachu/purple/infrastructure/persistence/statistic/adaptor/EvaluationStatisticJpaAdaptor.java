package com.pikachu.purple.infrastructure.persistence.statistic.adaptor;

import com.pikachu.purple.application.perfume.port.out.BrandRepository;
import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.BrandJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.BrandJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.EvaluationStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.repository.EvaluationStatisticJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.util.TimeUtil;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationStatisticJpaAdaptor implements EvaluationStatisticRepository {

    private final EvaluationStatisticJpaRepository evaluationStatisticJpaRepository;

    @Override
    public EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId) {
        String today = TimeUtil.today();

        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAllByTodayAndPerfumeIdOrderByVotesDesc(today, perfumeId);

        return EvaluationStatisticJpaEntity.toDomain(evaluationStatisticJpaEntities);
    }

}
