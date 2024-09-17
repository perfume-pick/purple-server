package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.StarRatingRepository;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.bootstrap.StarRating.vo.StarRatingInfo;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.User;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarRatingDomainServiceImpl implements StarRatingDomainService {

    private final StarRatingRepository starRatingRepository;

    @Override
    public void createOnboarding(
        Long userId,
        List<StarRatingInfo> starRatingInfos
    ) {
        List<Long> ratingIds = IntStream.range(0, starRatingInfos.size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        List<StarRating> starRatings = IntStream.range(0, ratingIds.size())
            .mapToObj(i -> StarRating.builder()
                .ratingId(ratingIds.get(i))
                .userId(userId)
                .perfumeId(starRatingInfos.get(i).perfumeId())
                .score(starRatingInfos.get(i).score())
                .build())
            .toList();

        starRatingRepository.createOnboarding(starRatings);
    }

    @Override
    public StarRating create(
        User user,
        Perfume perfume,
        int score
    ) {
        StarRating starRating = StarRating.builder()
            .user(user)
            .perfume(perfume)
            .score(score)
            .build();

        return starRatingRepository.create(starRating);
    }

    @Override
    public List<StarRating> getAllByUserId(Long userId) {
        return starRatingRepository.getAllByUserId(userId);
    }

    @Override
    public void updateScore(
        Long userId,
        Long ratingId,
        int score
    ) {
        StarRating starRating = starRatingRepository.getById(ratingId);

        starRating.updateScore(score);

        starRatingRepository.save(starRating);
    }

    @Override
    public StarRating findByPerfumeIdAndUserId(
        Long perfumeId,
        Long userId
    ) {
        return starRatingRepository.findByPerfumeIdAndUserId(
            perfumeId,
            userId
        );
    }

    @Override
    public void delete(StarRating starRating) {
        starRatingRepository.delete(starRating);
    }

}
