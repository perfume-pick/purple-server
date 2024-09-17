package com.pikachu.purple.bootstrap.review.controller;

import com.pikachu.purple.application.evaluation.port.in.EvaluationFormFieldGetUseCase;
import com.pikachu.purple.application.evaluation.port.in.EvaluationFormFieldGetUseCase.Result;
import com.pikachu.purple.application.review.port.in.CreateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.CreateReviewSimpleUseCase;
import com.pikachu.purple.application.review.port.in.ReviewDeleteUseCase;
import com.pikachu.purple.application.review.port.in.ReviewUpdateUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.review.api.ReviewApi;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewDetailRequest;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.request.UpdateSimpleReviewRequest;
import com.pikachu.purple.bootstrap.review.dto.response.GetEvaluationFormFieldResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final CreateReviewSimpleUseCase createReviewSimpleUseCase;
    private final CreateReviewDetailUseCase createReviewDetailUseCase;
    private final EvaluationFormFieldGetUseCase evaluationFormFieldGetUseCase;
    private final ReviewUpdateUseCase reviewUpdateUseCase;
    private final ReviewDeleteUseCase reviewDeleteUseCase;

    @Override
    public void createSimple(CreateReviewSimpleRequest request) {
        createReviewSimpleUseCase.invoke(
            new CreateReviewSimpleUseCase.Command(
                request.perfumeId(),
                request.score(),
                request.content()
            )
        );
    }

    @Override
    public void createDetail(CreateReviewDetailRequest request) {
        createReviewDetailUseCase.invoke(
            new CreateReviewDetailUseCase.Command(
                request.perfumeId(),
                request.score(),
                request.content(),
                request.evaluationFieldVOs(),
                request.moodNames()
            )
        );
    }

    @Override
    public SuccessResponse<GetEvaluationFormFieldResponse> findEvaluationFormField() {
        Result result = evaluationFormFieldGetUseCase.invoke();

        return SuccessResponse.of(
            new GetEvaluationFormFieldResponse(
                result.evaluationFieldDTOs(),
                result.moods()
        ));
    }

    @Override
    public void update(
        Long reviewId,
        UpdateSimpleReviewRequest request
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
