package com.pikachu.purple.infrastructure.persistence.statistic.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.StarRatingStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.StarRatingStatisticId;
import com.pikachu.purple.infrastructure.persistence.statistic.repository.StarRatingStatisticJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StarRatingStatisticJpaAdaptor implements StarRatingStatisticRepository {

    private final StarRatingStatisticJpaRepository starRatingStatisticJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;

    private StarRatingStatisticJpaEntity findEntity(
        Long perfumeId,
        int score
    ) {
        StarRatingStatisticId starRatingStatisticId = new StarRatingStatisticId(
            perfumeId,
            score
        );

        Optional<StarRatingStatisticJpaEntity> findResult =
            starRatingStatisticJpaRepository.findById(starRatingStatisticId);

        if (findResult.isEmpty()) {
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                .orElseThrow(() -> PerfumeNotFoundException);

            return StarRatingStatisticJpaEntity.builder()
                .perfumeId(perfumeJpaEntity.getId())
                .score(score)
                .build();

        } else {
            return findResult.get();
        }
    }

    @Override
    public List<StarRatingStatistic> findAll() {
        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities =
            starRatingStatisticJpaRepository.findAll();

        return starRatingStatisticJpaEntities.stream()
            .map(StarRatingStatisticJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId) {
        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities =
            starRatingStatisticJpaRepository.findAllByPerfumeIdOrderByScoreAsc(perfumeId);

        return starRatingStatisticJpaEntities.stream()
            .map(StarRatingStatisticJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<StarRatingStatistic> findAll(List<Long> perfumeIds) {
        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities =
            starRatingStatisticJpaRepository.findAllByPerfumeIdInOrderByScoreAsc(perfumeIds);

        return starRatingStatisticJpaEntities.stream()
            .map(StarRatingStatisticJpaEntity::toDomain)
            .toList();
    }

    @Override
    public void updateAll(List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs) {
        List<StarRatingStatisticJpaEntity> starRatingStatisticJpaEntities = new ArrayList<>();
        for (PerfumeStarRatingStatisticDTO perfumeStarRatingStatisticDTO
            : perfumeStarRatingStatisticDTOs) {
            Long perfumeId = perfumeStarRatingStatisticDTO.perfumeId();
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                .orElseThrow(() -> PerfumeNotFoundException);

            for (StarRatingStatistic starRatingStatistic :
                perfumeStarRatingStatisticDTO.starRatingStatistics()) {
                StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = StarRatingStatisticJpaEntity
                    .builder()
                    .perfumeId(perfumeJpaEntity.getId())
                    .score(starRatingStatistic.getScore())
                    .votes(starRatingStatistic.getVotes())
                    .build();
                starRatingStatisticJpaEntities.add(starRatingStatisticJpaEntity);
            }
        }

        starRatingStatisticJpaRepository.saveAll(starRatingStatisticJpaEntities);
    }

    @Override
    public void increaseVotes(
        Long perfumeId,
        int score
    ) {
        StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = findEntity(
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
        StarRatingStatisticJpaEntity starRatingStatisticJpaEntity = findEntity(
            perfumeId,
            score
        );

        starRatingStatisticJpaEntity.decrease();
        starRatingStatisticJpaRepository.save(starRatingStatisticJpaEntity);
    }

    @Override
    public StarRatingStatistic find(Long perfumeId, int score) {
        StarRatingStatisticJpaEntity starRatingStatistic = findEntity(
            perfumeId,
            score
        );

        return StarRatingStatisticJpaEntity.toDomain(starRatingStatistic);
    }

}
