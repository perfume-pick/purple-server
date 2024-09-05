package com.pikachu.purple.bootstrap.review.controller;

import com.pikachu.purple.application.evaluation.port.in.EvaluationFieldAndEvaluationMoodGetUseCase;
import com.pikachu.purple.application.evaluation.port.in.EvaluationFieldAndEvaluationMoodGetUseCase.Result;
import com.pikachu.purple.application.review.port.in.ReviewCreateSimpleUseCase;
import com.pikachu.purple.application.review.port.in.ReviewDeleteUseCase;
import com.pikachu.purple.application.review.port.in.ReviewUpdateUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.review.api.ReviewApi;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.request.UpdateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.response.GetEvaluationFieldAndEvaluationMoodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewCreateSimpleUseCase reviewCreateSimpleUseCase;
    private final EvaluationFieldAndEvaluationMoodGetUseCase evaluationFieldAndEvaluationMoodGetUseCase;
    private final ReviewUpdateUseCase reviewUpdateUseCase;
    private final ReviewDeleteUseCase reviewDeleteUseCase;

    @Override
    public void createSimple(CreateReviewSimpleRequest request) {
        reviewCreateSimpleUseCase.invoke(
            new ReviewCreateSimpleUseCase.Command(
                request.perfumeId(),
                request.score(),
                request.content()
            )
        );
    }

    @Override
    public SuccessResponse<GetEvaluationFieldAndEvaluationMoodResponse> findEvaluationFieldAndEvaluationMood() {
        Result result = evaluationFieldAndEvaluationMoodGetUseCase.invoke();

        return SuccessResponse.of(new GetEvaluationFieldAndEvaluationMoodResponse(
            result.evaluationFieldDTOS(),
            result.evaluationMoods()
        ));
    }

    @Override
    public void update(
        Long reviewId,
        UpdateReviewSimpleRequest request
    ) {
        reviewUpdateUseCase.invoke(
            new ReviewUpdateUseCase.Command(
                reviewId,
                request.ratingId(),
                request.score(),
                request.content()
            )
        );
    }

    @Override
    public void delete(Long reviewId) {
        reviewDeleteUseCase.invoke(reviewId);
    }

}
