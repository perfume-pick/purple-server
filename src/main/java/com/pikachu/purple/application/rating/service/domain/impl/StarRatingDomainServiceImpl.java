package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.StarRatingRepository;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingInfo;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
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
        starRatingRepository.createOnboarding(
            userId,
            starRatingInfos
        );
    }

    @Override
    public StarRating create(
        Long userId,
        Long perfumeId,
        int score
    ) {
        return starRatingRepository.create(userId, perfumeId, score);
    }

    @Override
    public List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId) {
        return starRatingRepository.findAllWithPerfumeAndPerfumeAccordByUserId(userId);
    }

    @Override
    public void updateScore(
        Long userId,
        Long perfumeId,
        int score
    ) {
        starRatingRepository.updateScore(
            userId,
            perfumeId,
            score
        );
    }

    @Override
    public void deleteByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        starRatingRepository.deleteByUserIdAndPerfumeId(
            userId,
            perfumeId
        );
    }

}
