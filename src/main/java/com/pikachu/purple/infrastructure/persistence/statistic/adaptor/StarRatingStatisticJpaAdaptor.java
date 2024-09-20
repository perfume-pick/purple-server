package com.pikachu.purple.infrastructure.persistence.statistic.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.StarRatingStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.repository.StarRatingStatisticJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.util.TimeUtil;
import com.pikachu.purple.infrastructure.persistence.statistic.vo.StarRatingStatisticCompositeKey;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarRatingStatisticJpaAdaptor implements StarRatingStatisticRepository {

    private final StarRatingStatisticJpaRepository starRatingStatisticJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;

    private StarRatingStatisticJpaEntity findEntityByToday(Long perfumeId, int score) {
        String today = TimeUtil.today();

        StarRatingStatisticCompositeKey compositeKey =
            StarRatingStatisticCompositeKey.builder()
                .statisticsDate(today)
                .perfumeId(perfumeId)
                .score(score)
                .build();

        Optional<StarRatingStatisticJpaEntity> findResult =
            starRatingStatisticJpaRepository.findByCompositeKey(compositeKey);

        StarRatingStatisticJpaEntity starRatingStatisticJpaEntity;
        if (findResult.isEmpty()) {
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                .orElseThrow(() -> PerfumeNotFoundException);

            return StarRatingStatisticJpaEntity.builder()
                .statisticsDate(today)
                .perfumeJpaEntity(perfumeJpaEntity)
                .score(score)
                .build();

        } else {
            return findResult.get();
        }
    }

    @Override
    public List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId) {
        String today = TimeUtil.today();

        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities =
            starRatingStatisticJpaRepository.findAllByTodayAndPerfumeId(
                today,
                perfumeId
            );

        return starRatingStatisticJpaEntities.stream()
            .map(StarRatingStatisticJpaEntity::toDomain)
            .toList();
    }

    @Override
    public void increaseVotes(
        Long perfumeId,
        int score
    ) {
        StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = findEntityByToday(perfumeId, score);

        starRatingStatisticJpaEntity.increase();
        starRatingStatisticJpaRepository.save(starRatingStatisticJpaEntity);
    }

    @Override
    public void decreaseVotes(
        Long perfumeId,
        int score
    ) {
        StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = findEntityByToday(perfumeId, score);

        starRatingStatisticJpaEntity.decrease();
        starRatingStatisticJpaRepository.save(starRatingStatisticJpaEntity);
    }

}
