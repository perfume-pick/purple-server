package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.SortType;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetReviewsService implements GetReviewsUseCase {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Result findAll(
        Long userId,
        Long perfumeId,
        String sortType
    ) {
        SortType getSortType = SortType.transByStr(sortType);

        List<Review> reviews = new ArrayList<>();

        switch (getSortType) {
            case LIKED:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByLikeCountDesc(
                    userId,
                    perfumeId
                );
                break;
            case LATEST:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByCreatedAtDesc(
                    userId,
                    perfumeId
                );
                break;
            case STAR_RATING_HIGH:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByScoreDesc(
                    userId,
                    perfumeId
                );
                break;
            case STAR_RATING_LOW:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByScoreAsc(
                    userId,
                    perfumeId
                );
                break;
            default:
                break;
        }

        return new Result(reviews);
    }

}
