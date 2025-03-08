package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.review.port.in.starrating.GetPerfumeAverageScoreUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetPerfumeAverageScoreService implements GetPerfumeAverageScoreUseCase {

    private final StarRatingRepository starRatingRepository;

    @Transactional
    @Override
    public Result findByPerfumeId(Long perfumeId) {
        List<StarRating> starRatings = starRatingRepository.findAllByPerfumeId(perfumeId);
        double totalScore = starRatings.stream().mapToInt(StarRating::getScore).sum();
        double averageScore = Math.round(totalScore / starRatings.size() * 10) / 10.0;
        return new Result(averageScore);
    }

}
