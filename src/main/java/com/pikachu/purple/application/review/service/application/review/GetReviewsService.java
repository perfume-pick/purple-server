package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.common.dto.ReviewDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationFieldDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationOptionDTO;
import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.SortType;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetReviewsService implements
    GetReviewsUseCase {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Result invoke(
        Long perfumeId,
        String sortType
    ) {
        Long userId = getCurrentUserAuthentication().userId();
        SortType getSortType = SortType.transByStr(sortType);

        List<Review> reviews = new ArrayList<>();

        switch (getSortType) {
            case LIKED:
                reviews = reviewRepository.findAllOrderByLikeCountDesc(
                    userId,
                    perfumeId
                );
                break;
            case LATEST:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByCreatedAtDesc(
                    userId,
                    perfumeId
                );
                break;
            case STAR_RATING_HIGH:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreDesc(
                    userId,
                    perfumeId
                );
                break;
            case STAR_RATING_LOW:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreAsc(
                    userId,
                    perfumeId
                );
                break;
            default:
                break;
        }

        List<ReviewDTO> reviewDTOs = reviews.stream()
            .map(review -> {
                List<ReviewEvaluationFieldDTO> reviewEvaluation = review.getEvaluation()
                    .getFields(review.getId()).stream()
                    .map(fieldType ->
                        ReviewEvaluationFieldDTO.of(
                            fieldType,
                            review.getEvaluation().getOptions(review.getId(), fieldType).stream()
                                .map(ReviewEvaluationOptionDTO::of)
                                .toList()
                        )
                    ).toList();

                List<String> moodNames = review.getMoods().stream()
                    .map(Mood::getName)
                    .toList();

                return ReviewDTO.of(
                    userId,
                    review,
                    reviewEvaluation,
                    moodNames
                );
            })
            .toList();

        return new Result(reviewDTOs);
    }

}
