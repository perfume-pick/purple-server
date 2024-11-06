package com.pikachu.purple.application.review.service.application.starrating;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.common.dto.ReviewEvaluationFieldDTO;
import com.pikachu.purple.application.review.common.dto.ReviewEvaluationOptionDTO;
import com.pikachu.purple.application.review.common.dto.ReviewWithPerfumeDTO;
import com.pikachu.purple.application.review.port.in.starrating.GetReviewsByUserAndSortTypeUseCase;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.SortType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetReviewsByUserAndSortTypeApplicationService implements
    GetReviewsByUserAndSortTypeUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Transactional
    @Override
    public Result invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();
        SortType sortType = SortType.transByStr(command.sortType());

        List<StarRating> starRatings = new ArrayList<>();
        switch (sortType) {
            case LIKED:
                starRatings = starRatingDomainService.findAllOrderByLikeCountDesc(userId);
                break;
            case LATEST:
                starRatings = starRatingDomainService.findAllByUserId(userId);

                starRatings = starRatings.stream()
                    .sorted(Comparator.comparing(
                        (StarRating sr) -> sr.getReview() != null ? sr.getReview().getUpdatedAt() : sr.getUpdatedAt()
                    ).reversed())
                    .toList();
                break;
            case STAR_RATING_HIGH:
                starRatings = starRatingDomainService.findAllOrderByScoreDesc(userId);
                break;
            case STAR_RATING_LOW:
                starRatings = starRatingDomainService.findAllOrderByScoreAsc(userId);
                break;
            default:
                break;
        }

        List<ReviewWithPerfumeDTO> reviewWithPerfumeDTOs = starRatings.stream()
            .map(starRating -> {
                if (starRating.getReview() != null) {
                    List<ReviewEvaluationFieldDTO> reviewEvaluations = starRating.getReview().getEvaluation()
                        .getFields(starRating.getReview().getId()).stream()
                        .map(fieldType -> ReviewEvaluationFieldDTO.of(
                            fieldType,
                            starRating.getReview().getEvaluation().getOptions(
                                    starRating.getReview().getId(),
                                    fieldType
                                ).stream()
                                .map(ReviewEvaluationOptionDTO::of)
                                .toList()
                        )).toList();

                    List<String> moodNames = starRating.getReview().getMoods().stream()
                        .map(Mood::getName)
                        .toList();

                    return ReviewWithPerfumeDTO.of(
                        starRating,
                        reviewEvaluations,
                        moodNames
                    );
                } else {
                   return ReviewWithPerfumeDTO.from(starRating);
                }
            })
            .toList();

        return new Result(reviewWithPerfumeDTOs);
    }

}
