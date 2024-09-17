package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.StarRatingRepository;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.bootstrap.StarRating.vo.StarRatingInfo;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarRatingDomainServiceImpl implements StarRatingDomainService {

    private final StarRatingRepository starRatingRepository;

    @Override
    public void createOnboarding(
        User user,
        List<Perfume> perfumes,
        List<StarRatingInfo> starRatingInfos
    ) {

        Map<Long, Perfume> perfumeMap = perfumes.stream()
            .collect(Collectors.toMap(Perfume::getId, perfume -> perfume));

        List<StarRating> starRatings = starRatingInfos.stream()
            .map(info -> {
                Perfume perfume = perfumeMap.get(info.perfumeId());
                return StarRating.builder()
                    .user(user)
                    .perfume(perfume)
                    .score(info.score())
                    .build();
            })
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
    public List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId) {
        return starRatingRepository.findAllWithPerfumeAndPerfumeAccordByUserId(userId);
    }

    @Override
    public void updateScore(
        Long userId,
        Long perfumeId,
        int score
    ) {
        StarRating starRating = starRatingRepository.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        starRating.updateScore(score);

        starRatingRepository.update(starRating);
    }

    @Override
    public StarRating findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        return starRatingRepository.findByUserIdAndPerfumeId(
            userId,
            perfumeId
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
