package com.pikachu.purple.infrastructure.persistence.statistic.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.StarRatingStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.repository.StarRatingStatisticJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.vo.StarRatingStatisticCompositeKey;
import com.pikachu.purple.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarRatingStatisticJpaAdaptor implements StarRatingStatisticRepository {

    private final StarRatingStatisticJpaRepository starRatingStatisticJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;

    private StarRatingStatisticJpaEntity findEntity(
        String statisticsDate,
        Long perfumeId,
        int score
    ) {
        StarRatingStatisticCompositeKey compositeKey =
            StarRatingStatisticCompositeKey.builder()
                .statisticsDate(statisticsDate)
                .perfumeId(perfumeId)
                .score(score)
                .build();

        Optional<StarRatingStatisticJpaEntity> findResult =
            starRatingStatisticJpaRepository.findByCompositeKey(compositeKey);

        if (findResult.isEmpty()) {
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                .orElseThrow(() -> PerfumeNotFoundException);

            return StarRatingStatisticJpaEntity.builder()
                .statisticsDate(statisticsDate)
                .perfumeJpaEntity(perfumeJpaEntity)
                .score(score)
                .build();

        } else {
            return findResult.get();
        }
    }

    private StarRatingStatisticJpaEntity findEntityByToday(Long perfumeId, int score) {
        String today = DateUtil.today();

        return findEntity(today, perfumeId, score);
    }

    @Override
    public List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId) {
        String today = DateUtil.today();

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
        StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = findEntityByToday(
            perfumeId,
            score
        );

        starRatingStatisticJpaEntity.increase();
        starRatingStatisticJpaRepository.save(starRatingStatisticJpaEntity);
    }

    @Override
    public void decreaseVotes(
        Long perfumeId,
        int score
    ) {
        StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = findEntityByToday(
            perfumeId,
            score
        );

        starRatingStatisticJpaEntity.decrease();
        starRatingStatisticJpaRepository.save(starRatingStatisticJpaEntity);
    }

    @Override
    public StarRatingStatistic findByPerfumeIdAndScore(Long perfumeId, int score) {
        StarRatingStatisticJpaEntity starRatingStatistic = findEntityByToday(
            perfumeId,
            score
        );

        return StarRatingStatisticJpaEntity.toDomain(starRatingStatistic);
    }

    @Override
    public List<StarRatingStatistic> findAll(String statisticsDate) {

        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities =
            starRatingStatisticJpaRepository.findAllByStatisticsDate(statisticsDate);

        return starRatingStatisticJpaEntities.stream()
            .map(StarRatingStatisticJpaEntity::toDomain)
            .toList();

    }

    @Override
    public void updateAll(
        String statisticsDate,
        List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs
    ) {

        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities = new ArrayList<>();
        for (PerfumeStarRatingStatisticDTO perfumeStarRatingStatisticDTO : perfumeStarRatingStatisticDTOs) {
            Long perfumeId = perfumeStarRatingStatisticDTO.perfumeId();
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                .orElseThrow(() -> PerfumeNotFoundException);

            for (StarRatingStatistic starRatingStatistic : perfumeStarRatingStatisticDTO.starRatingStatistics()) {
                StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = StarRatingStatisticJpaEntity
                    .builder()
                    .statisticsDate(statisticsDate)
                    .perfumeJpaEntity(perfumeJpaEntity)
                    .score(starRatingStatistic.getScore())
                    .votes(starRatingStatistic.getVotes())
                    .build();
                starRatingStatisticJpaEntities.add(starRatingStatisticJpaEntity);
            }
        }

        starRatingStatisticJpaRepository.saveAll(
            starRatingStatisticJpaEntities);
    }

}
