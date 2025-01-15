package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.common.dto.ReviewDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationFieldDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationOptionDTO;
import com.pikachu.purple.application.review.port.in.review.GetReviewsByPerfumeIdAndSortTypeUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
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
public class GetReviewsByPerfumeIdAndSortTypeApplicationService implements
    GetReviewsByPerfumeIdAndSortTypeUseCase {

    private final ReviewDomainService reviewDomainService;

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
                reviews = reviewDomainService.findAllOrderByLikeCountDesc(
                    userId,
                    perfumeId
                );
                break;
            case LATEST:
                reviews = reviewDomainService.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByCreatedAtDesc(
                    userId,
                    perfumeId
                );
                break;
            case STAR_RATING_HIGH:
                reviews = reviewDomainService.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreDesc(
                    userId,
                    perfumeId
                );
                break;
            case STAR_RATING_LOW:
                reviews = reviewDomainService.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreAsc(
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
