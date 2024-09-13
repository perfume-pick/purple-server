package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.RatingRepository;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.bootstrap.rating.vo.RatingInfo;
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
        Long userId,
        List<RatingInfo> ratingInfos
    ) {
        List<Long> ratingIds = IntStream.range(0, ratingInfos.size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        List<Rating> ratings = IntStream.range(0, ratingIds.size())
            .mapToObj(i -> Rating.builder()
                .ratingId(ratingIds.get(i))
                .userId(userId)
                .perfumeId(ratingInfos.get(i).perfumeId())
                .score(ratingInfos.get(i).score())
                .build())
            .toList();

        ratingRepository.createOnboarding(ratings);
    }

    @Override
    public void create(
        Long userId,
        Long perfumeId,
        int score
    ) {
        Long ratingId = IdGenerator.generate();

        Rating rating = Rating.builder()
            .ratingId(ratingId)
            .userId(userId)
            .perfumeId(perfumeId)
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
        Long ratingId,
        int score
    ) {
        Rating rating = ratingRepository.getById(ratingId);

        rating.updateScore(score);

        ratingRepository.save(rating);
    }

    @Override
    public Rating findByPerfumeIdAndUserId(
        Long perfumeId,
        Long userId
    ) {
        return ratingRepository.findByPerfumeIdAndUserId(
            perfumeId,
            userId
        );
    }

    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

}
