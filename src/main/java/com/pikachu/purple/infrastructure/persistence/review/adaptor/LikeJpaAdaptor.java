package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.LikeAlreadyExistedException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.LikeNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ReviewNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.review.port.out.LikeRepository;
import com.pikachu.purple.domain.review.Like;
import com.pikachu.purple.infrastructure.persistence.review.entity.LikeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.LikeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeJpaAdaptor implements LikeRepository {

    private final LikeJpaRepository likeJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public Like find(
        Long userId,
        Long reviewId
    ) {
        LikeJpaEntity likeJpaEntity = likeJpaRepository.findByUserIdAndReviewId(
            userId,
            reviewId
        ).orElseThrow(() -> LikeNotFoundException);

        return LikeJpaEntity.toDomain(likeJpaEntity);
    }

    @Override
    public void create(
        Long userId,
        Long reviewId
    ) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        ReviewJpaEntity reviewJpaEntity = reviewJpaRepository.findById(reviewId)
            .orElseThrow(() -> ReviewNotFoundException);

        LikeJpaEntity likeJpaEntity = LikeJpaEntity.builder()
            .userJpaEntity(userJpaEntity)
            .reviewJpaEntity(reviewJpaEntity)
            .build();

        likeJpaRepository.save(likeJpaEntity);
    }

    @Override
    public void validateNotExist(
        Long userId,
        Long reviewId
    ) {
        likeJpaRepository.findByUserIdAndReviewId(
            userId,
            reviewId
        ).ifPresent(likeJpaEntity -> {throw LikeAlreadyExistedException;});
    }

    @Override
    public void delete(
        Long userId,
        Long reviewId
    ) {
        LikeJpaEntity likeJpaEntity = likeJpaRepository.findByUserIdAndReviewId(
            userId,
            reviewId
        ).orElseThrow(() -> LikeNotFoundException);

        likeJpaRepository.delete(likeJpaEntity);
    }

    @Override
    public void deleteAll(Long reviewId) {
        List<LikeJpaEntity> likeJpaEntities = likeJpaRepository.findAllByReviewId(reviewId);

        likeJpaRepository.deleteAll(likeJpaEntities);
    }

}
