package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.StarRatingNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.StarRatingJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StarRatingJpaAdaptor implements StarRatingRepository {

    private final StarRatingJpaRepository starRatingJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;
    private final UserJpaRepository userJpaRepository;

    private StarRatingJpaEntity findEntityOrThrowException(
        Long userId,
        Long perfumeId
    ) {
        return starRatingJpaRepository.findByUserIdAndPerfumeId(
            userId, perfumeId).orElseThrow(() -> StarRatingNotFoundException);
    }

    @Override
    public StarRating create(
        Long starRatingId,
        Long userId,
        Long perfumeId,
        int score
    ) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
            .orElseThrow(() -> PerfumeNotFoundException);

        StarRatingJpaEntity starRatingJpaEntity = StarRatingJpaEntity.builder()
            .id(starRatingId)
            .userId(userJpaEntity.getId())
            .perfumeId(perfumeJpaEntity.getId())
            .score(score)
            .build();

        StarRatingJpaEntity starRatingJpaEntitySaved = starRatingJpaRepository.save(starRatingJpaEntity);

        return StarRatingJpaEntity.toDomain(starRatingJpaEntitySaved);
    }

    @Override
    public List<StarRating> createAll(
        Long userId,
        List<StarRating> starRatings
    ) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        List<StarRatingJpaEntity> starRatingJpaEntities = new ArrayList<>();
        for (StarRating starRating: starRatings) {
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository
                .findById(starRating.getPerfume().getId())
                .orElseThrow(() -> PerfumeNotFoundException);

            starRatingJpaEntities.add(
                StarRatingJpaEntity.builder()
                    .id(starRating.getId())
                    .userId(userJpaEntity.getId())
                    .perfumeId(perfumeJpaEntity.getId())
                    .score(starRating.getScore())
                    .build()
            );
        }

        starRatingJpaEntities = starRatingJpaRepository.saveAll(starRatingJpaEntities);

        return starRatingJpaEntities.stream().map(StarRatingJpaEntity::toDomain).toList();
    }

    @Override
    public StarRating findByStarRatingId(Long starRatingId) {
        Optional<StarRatingJpaEntity> findResult = starRatingJpaRepository.findById(starRatingId);

        return findResult.map(StarRatingJpaEntity::toDomain).orElse(null);
    }

    @Override
    public StarRating findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        Optional<StarRatingJpaEntity> findResult = starRatingJpaRepository.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        return findResult.map(StarRatingJpaEntity::toDomain).orElse(null);
    }

    @Override
    public List<StarRating> findAll() {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAll();
        return starRatingJpaEntities.stream().map(StarRatingJpaEntity::toDomain).toList();
    }

    @Override
    public List<StarRating> findAllByPerfumeId(Long perfumeId) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllByPerfumeId(perfumeId);
        return starRatingJpaEntities.stream().map(StarRatingJpaEntity::toDomain).toList();
    }

    @Override
    public List<StarRating> findAllByUserId(Long userId) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllByUserId(userId);

        return starRatingJpaEntities.stream()
            .map(StarRatingJpaEntity::toDomain)
            .toList();
    }

    @Override
    public StarRating updateScore(
        Long userId,
        Long perfumeId,
        int score
    ) {
        StarRatingJpaEntity starRatingJpaEntity = findEntityOrThrowException(
            userId,
            perfumeId
        );

        starRatingJpaEntity.updateScore(score);
        StarRatingJpaEntity starRatingJpaEntitySaved = starRatingJpaRepository.save(starRatingJpaEntity);
        return StarRatingJpaEntity.toDomain(starRatingJpaEntitySaved);
    }

    @Override
    public StarRating deleteById(Long starRatingId) {
        StarRatingJpaEntity starRatingJpaEntity = starRatingJpaRepository.findById(starRatingId)
            .orElseThrow(() -> StarRatingNotFoundException);

        starRatingJpaRepository.delete(starRatingJpaEntity);

        return StarRatingJpaEntity.toDomain(starRatingJpaEntity);
    }

}
