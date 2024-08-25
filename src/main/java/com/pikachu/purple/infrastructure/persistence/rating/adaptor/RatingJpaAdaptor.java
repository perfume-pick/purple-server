package com.pikachu.purple.infrastructure.persistence.rating.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.RatingNotFoundException;

import com.pikachu.purple.application.rating.port.out.RatingRepository;
import com.pikachu.purple.domain.rating.Rating;
import com.pikachu.purple.infrastructure.persistence.rating.entity.RatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.rating.repository.RatingJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingJpaAdaptor implements RatingRepository {

    private final RatingJpaRepository ratingJpaRepository;

    @Override
    public void createOnboarding(List<Rating> ratings) {
        List<RatingJpaEntity> ratingJpaEntities = ratings.stream()
            .map(RatingJpaEntity::toJpaEntity)
            .toList();

        ratingJpaRepository.saveAll(ratingJpaEntities);
    }

    @Override
    public Long create(Rating rating) {
        RatingJpaEntity ratingJpaEntity = RatingJpaEntity.toJpaEntity(rating);
        return ratingJpaRepository.save(ratingJpaEntity).getRatingId();
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        List<RatingJpaEntity> ratingJpaEntities = ratingJpaRepository.findByUserId(userId);

        return ratingJpaEntities.stream()
            .map(RatingJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Rating getById(Long ratingId) {
        RatingJpaEntity ratingJpaEntity = ratingJpaRepository.findById(ratingId)
            .orElseThrow(() -> RatingNotFoundException);

        return RatingJpaEntity.toDomain(ratingJpaEntity);
    }

    @Override
    public void save(Rating rating) {
        RatingJpaEntity ratingJpaEntity = RatingJpaEntity.toJpaEntity(rating);
        ratingJpaRepository.save(ratingJpaEntity);
    }

    @Override
    public Rating getByIdAndUserId(
        Long ratingId,
        Long userId
    ) {
        RatingJpaEntity ratingJpaEntity = ratingJpaRepository.findByRatingIdAndUserId(
            ratingId,
            userId
        ).orElseThrow(() -> RatingNotFoundException);

        return RatingJpaEntity.toDomain(ratingJpaEntity);
    }

    @Override
    public void delete(Rating rating) {
        RatingJpaEntity ratingJpaEntity = RatingJpaEntity.toJpaEntity(rating);
        ratingJpaRepository.delete(ratingJpaEntity);
    }

}
