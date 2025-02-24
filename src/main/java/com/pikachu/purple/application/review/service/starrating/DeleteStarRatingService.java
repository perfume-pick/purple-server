package com.pikachu.purple.application.review.service.starrating;

import com.pikachu.purple.application.review.port.in.starrating.DeleteStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.DecreaseStarRatingStatisticUseCase;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteStarRatingService implements DeleteStarRatingUseCase {

    private final StarRatingRepository starRatingRepository;

    private final DecreaseStarRatingStatisticUseCase decreaseStarRatingStatisticUseCase;

    @Transactional
    @Override
    public void delete(Long starRatingId) {
        StarRating starRating = starRatingRepository.deleteById(starRatingId);

        decreaseStarRatingStatisticUseCase.invoke(
            starRating.getPerfume().getId(),
            starRating.getScore()
        );
    }

}
