package com.pikachu.purple.application.review.service.application.starrating;

import com.pikachu.purple.application.review.port.in.starrating.GetAverageScoreByPerfumeIdUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAverageScoreByPerfumeIdApplicationService implements
    GetAverageScoreByPerfumeIdUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public Result invoke(Long perfumeId) {
        List<StarRating> starRatings = starRatingDomainService.findAll(perfumeId);
        double totalScore = starRatings.stream().mapToInt(StarRating::getScore).sum();
        double averageScore = Math.round(totalScore / starRatings.size() * 10) / 10.0;
        return new Result(averageScore);
    }

}
