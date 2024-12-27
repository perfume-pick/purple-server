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
public class StarRatingJpaAdaptor implements StarRatingRepository {

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
    public void createOnboarding(
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
                    .userJpaEntity(userJpaEntity)
                    .perfumeJpaEntity(perfumeJpaEntity)
                    .score(starRating.getScore())
                    .build()
            );
        }

        starRatingJpaRepository.saveAll(starRatingJpaEntities);
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
            .userJpaEntity(userJpaEntity)
            .perfumeJpaEntity(perfumeJpaEntity)
            .score(score)
            .build();

        StarRatingJpaEntity starRatingJpaEntitySaved = starRatingJpaRepository.save(starRatingJpaEntity);

        return StarRatingJpaEntity.toDomain(starRatingJpaEntitySaved);
    }

    @Override
    public List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllByUserJpaEntity(userJpaEntity);

        return starRatingJpaEntities.stream()
            .map(StarRatingJpaEntity::toDomainWithPerfumeAccord)
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

    @Override
    public StarRating findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        Optional<StarRatingJpaEntity> findResult = starRatingJpaRepository.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        return findResult.map(StarRatingJpaEntity::toDomainWithPerfumeAccord).orElse(null);
    }

    @Override
    public List<StarRating> findAll() {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAll();
        return starRatingJpaEntities.stream().map(StarRatingJpaEntity::toDomain).toList();
    }

    @Override
    public List<StarRating> findAll(Long perfumeId) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllByPerfumeId(perfumeId);
        return starRatingJpaEntities.stream().map(StarRatingJpaEntity::toDomain).toList();
    }

    @Override
    public List<StarRating> findAllByUpdatedDate(String updatedDate) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllByUpdatedDate(
            updatedDate);

        return starRatingJpaEntities.stream()
            .map(StarRatingJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<StarRating> findAllOrderByLikeCountDesc(Long userId) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllOrderByLikeCountDesc(userId);

        return starRatingJpaEntities.stream()
            .map(starRatingJpaEntity -> {
                if (starRatingJpaEntity.getReviewJpaEntity() != null) {
                    return StarRatingJpaEntity.toFullDomain(starRatingJpaEntity, userId);
                } else {
                    return StarRatingJpaEntity.toDomainWithPerfume(starRatingJpaEntity);
                }
            })
            .toList();
    }

    @Override
    public List<StarRating> findAllByUserId(Long userId) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllByUserId(userId);

        return starRatingJpaEntities.stream()
            .map(starRatingJpaEntity -> {
                if (starRatingJpaEntity.getReviewJpaEntity() != null) {
                    return StarRatingJpaEntity.toFullDomain(starRatingJpaEntity, userId);
                } else {
                    return StarRatingJpaEntity.toDomainWithPerfume(starRatingJpaEntity);
                }
            })
            .toList();
    }

    @Override
    public List<StarRating> findAllOrderByScoreDesc(Long userId) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllOrderByScoreDesc(userId);

        return starRatingJpaEntities.stream()
            .map(starRatingJpaEntity -> {
                if (starRatingJpaEntity.getReviewJpaEntity() != null) {
                    return StarRatingJpaEntity.toFullDomain(starRatingJpaEntity, userId);
                } else {
                    return StarRatingJpaEntity.toDomainWithPerfume(starRatingJpaEntity);
                }
            })
            .toList();
    }

    @Override
    public List<StarRating> findALlOrderByScoreAsc(Long userId) {
        List<StarRatingJpaEntity> starRatingJpaEntities = starRatingJpaRepository.findAllOrderByScoreAsc(userId);

        return starRatingJpaEntities.stream()
            .map(starRatingJpaEntity -> {
                if (starRatingJpaEntity.getReviewJpaEntity() != null) {
                    return StarRatingJpaEntity.toFullDomain(starRatingJpaEntity, userId);
                } else {
                    return StarRatingJpaEntity.toDomainWithPerfume(starRatingJpaEntity);
                }
            })
            .toList();
    }

}
