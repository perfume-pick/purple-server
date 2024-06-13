package com.pikachu.purple.infrastructure.persistence.rating.adaptor;

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
    public void create(List<Rating> ratingList) {
        List<RatingJpaEntity> ratingJpaEntities = ratingList.stream()
            .map(RatingJpaEntity::toJpaEntity)
            .toList();

        ratingJpaRepository.saveAll(ratingJpaEntities);
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        List<RatingJpaEntity> ratingJpaEntities = ratingJpaRepository.findByUserId(userId);

        return ratingJpaEntities.stream()
            .map(RatingJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
