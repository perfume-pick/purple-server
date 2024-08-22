package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.RatingRepository;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.bootstrap.review.vo.RatingValue;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingDomainServiceImpl implements RatingDomainService {

    private final RatingRepository ratingRepository;

    @Override
    public void createOnboarding(
        List<Long> ratingIds,
        Long userId,
        List<Long> reviewIds,
        List<RatingValue> ratingValues
    ) {
        List<Rating> ratings = IntStream.range(0, ratingIds.size())
            .mapToObj(i -> Rating.builder()
                .ratingId(ratingIds.get(i))
                .userId(userId)
                .reviewId(reviewIds.get(i))
                .score(ratingValues.get(i).score())
                .build())
            .toList();

        ratingRepository.createOnboarding(ratings);
    }

    @Override
    public void create(
        Long ratingId,
        Long userId,
        Long reviewId,
        Double score
    ) {
        Rating rating = Rating.builder()
            .ratingId(ratingId)
            .userId(userId)
            .reviewId(reviewId)
            .score(score)
            .build();

        ratingRepository.create(rating);
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        return ratingRepository.getAllByUserId(userId);
    }

    @Override
    public void updateScore(
        Long userId,
        Long reviewId,
        Double score
    ) {
        Rating rating = ratingRepository.getByUserIdAndReviewId(
            userId,
            reviewId
        );

        rating.updateScore(score);

        ratingRepository.save(rating);
    }

}
