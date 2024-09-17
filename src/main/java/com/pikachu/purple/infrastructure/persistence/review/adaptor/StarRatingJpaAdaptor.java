package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import com.pikachu.purple.application.rating.port.out.StarRatingRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.StarRatingJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarRatingJpaAdaptor implements StarRatingRepository {

    private final StarRatingJpaRepository starRatingJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void createOnboarding(List<StarRating> starRatings) {

    }

    @Override
    public StarRating create(Long userId, Long perfumeId, int score) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
            .orElseThrow(() -> new BusinessException(ErrorCode.PERFUME_NOT_FOUND));

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
        return List.of();
    }

    @Override
    public void update(StarRating starRating) {

    }

    @Override
    public StarRating findByUserIdAndPerfumeId(Long userId, Long perfumeId) {
        StarRatingJpaEntity starRatingJpaEntity = starRatingJpaRepository.findByUserIdAndPerfumeId(
            userId, perfumeId).orElseThrow(() -> new BusinessException(ErrorCode.STAR_RATING_NOT_FOUND));

        return StarRatingJpaEntity.toDomain(starRatingJpaEntity);
    }

    @Override
    public void deleteByUserIdAndPerfumeId(Long userId, Long perfumeId) {

    }
}
