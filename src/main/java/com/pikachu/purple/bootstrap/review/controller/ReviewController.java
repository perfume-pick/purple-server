package com.pikachu.purple.bootstrap.review.controller;

import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase;
import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase.Result;
import com.pikachu.purple.application.review.port.in.review.CreateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.review.CreateReviewSimpleUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
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
    private final GetEvaluationFormFieldUseCase getEvaluationFormFieldUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;

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
        Result result = getEvaluationFormFieldUseCase.invoke();

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
        updateReviewUseCase.invoke(
            new UpdateReviewUseCase.Command(
                reviewId,
                request.score(),
                request.content()
            )
        );
    }

    @Override
    public void delete(Long reviewId) {
        deleteReviewUseCase.invoke(new DeleteReviewUseCase.Command(reviewId));
    }

}
