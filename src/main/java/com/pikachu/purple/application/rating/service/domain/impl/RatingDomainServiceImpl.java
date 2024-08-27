package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.RatingRepository;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.bootstrap.rating.vo.RatingValue;
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
        List<RatingValue> ratingValues
    ) {
        List<Rating> ratings = IntStream.range(0, ratingIds.size())
            .mapToObj(i -> Rating.builder()
                .ratingId(ratingIds.get(i))
                .userId(userId)
                .perfumeId(ratingValues.get(i).perfumeId())
                .score(ratingValues.get(i).score())
                .build())
            .toList();

        ratingRepository.createOnboarding(ratings);
    }

    @Override
    public Rating create(
        Long ratingId,
        Long userId,
        Long perfumeId,
        int score
    ) {
        Rating rating = Rating.builder()
            .ratingId(ratingId)
            .userId(userId)
            .perfumeId(perfumeId)
            .score(score)
            .build();

        return ratingRepository.create(rating);
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        return ratingRepository.getAllByUserId(userId);
    }

    @Override
    public void updateScore(
        Long userId,
        Long ratingId,
        int score
    ) {
        Rating rating = ratingRepository.getById(ratingId);

        rating.updateScore(score);

        ratingRepository.save(rating);
    }

    @Override
    public Rating getByIdAndUserId(
        Long ratingId,
        Long userId
    ) {
        return ratingRepository.getByIdAndUserId(
            ratingId,
            userId
        );
    }

    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

}
