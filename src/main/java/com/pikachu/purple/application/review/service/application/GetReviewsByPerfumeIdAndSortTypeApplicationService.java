package com.pikachu.purple.application.review.service.application;

import com.pikachu.purple.application.review.common.dto.ReviewDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationFieldDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationOptionDTO;
import com.pikachu.purple.application.review.port.in.GetReviewsByPerfumeIdAndSortTypeUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.SortType;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetReviewsByPerfumeIdAndSortTypeApplicationService implements
    GetReviewsByPerfumeIdAndSortTypeUseCase {

    private final ReviewDomainService reviewDomainService;

    @Transactional
    @Override
    public Result invoke(Command command) {
        SortType sortType = SortType.transByStr(command.sortType());

        List<Review> reviews = new ArrayList<>();

        switch (sortType) {
            case LIKED:
                break;
            case LATEST:
                reviews = reviewDomainService.findAllWithReviewEvaluationAndMoodOrderByCreatedAtDesc(command.perfumeId());
                break;
            case STAR_RATING_HIGH:
//                reviews = reviewDomainService.findAllWithReviewEvaluationAndMoodOrderByScoreDesc(command.perfumeId());
                break;
            case STAR_RATING_LOW:
//                reviews = reviewDomainService.findAllWithReviewEvaluationAndMoodOrderByScoreAsc(command.perfumeId());
                break;
            default:
                break;
        }

        List<ReviewDTO> reviewDTOs = reviews.stream()
            .map(review -> {
                List<ReviewEvaluationFieldDTO> reviewEvaluation = review.getEvaluation().getFields().stream()
                    .map(evaluationField ->
                        ReviewEvaluationFieldDTO.of(
                            evaluationField.getType(),
                            evaluationField.getOptions().stream()
                                .map(option -> ReviewEvaluationOptionDTO.of(option.getType()))
                                .toList()
                        )
                    ).toList();

                List<String> moodNames = review.getMoods().stream()
                    .map(Mood::getName)
                    .toList();

                return ReviewDTO.of(
                    review,
                    reviewEvaluation,
                    moodNames
                );
            })
            .toList();

        return new Result(reviewDTOs);
    }

}
