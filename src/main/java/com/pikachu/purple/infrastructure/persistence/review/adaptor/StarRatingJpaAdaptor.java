package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.StarRatingNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.rating.port.out.StarRatingRepository;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingInfo;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.StarRatingJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarRatingJpaAdaptor implements StarRatingRepository {

    private final StarRatingJpaRepository starRatingJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;
    private final UserJpaRepository userJpaRepository;

    private StarRatingJpaEntity findEntityByUserIdAndPerfumeId(Long userId, Long perfumeId) {
        return starRatingJpaRepository.findByUserIdAndPerfumeId(
            userId, perfumeId).orElseThrow(() -> StarRatingNotFoundException);
    }

    @Override
    public void createOnboarding(
        Long userId,
        List<StarRatingInfo> starRatingInfos
    ) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        List<StarRatingJpaEntity> starRatingJpaEntities = new ArrayList<>();
        for (StarRatingInfo starRatingInfo: starRatingInfos) {
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository
                .findById(starRatingInfo.perfumeId())
                .orElseThrow(() -> PerfumeNotFoundException);

            starRatingJpaEntities.add(
                StarRatingJpaEntity.builder()
                    .userJpaEntity(userJpaEntity)
                    .perfumeJpaEntity(perfumeJpaEntity)
                    .score(starRatingInfo.score())
                    .build()
            );
        }

        starRatingJpaRepository.saveAll(starRatingJpaEntities);
    }

    @Override
    public StarRating create(Long userId, Long perfumeId, int score) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
            .orElseThrow(() -> PerfumeNotFoundException);

        StarRatingJpaEntity starRatingJpaEntity = StarRatingJpaEntity.builder()
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
    public void updateScore(Long userId, Long perfumeId, int score) {
        StarRatingJpaEntity starRatingJpaEntity = findEntityByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        starRatingJpaEntity.updateScore(score);
        starRatingJpaRepository.save(starRatingJpaEntity);
    }

    @Override
    public void deleteByUserIdAndPerfumeId(Long userId, Long perfumeId) {
        StarRatingJpaEntity starRatingJpaEntity = findEntityByUserIdAndPerfumeId(
            userId,
            perfumeId
        );
        starRatingJpaRepository.delete(starRatingJpaEntity);
    }
}
