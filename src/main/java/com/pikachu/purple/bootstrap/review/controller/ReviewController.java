package com.pikachu.purple.bootstrap.review.controller;

import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase;
import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase.Result;
import com.pikachu.purple.application.review.port.in.complaint.CreateComplaintUseCase;
import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintUseCase;
import com.pikachu.purple.application.review.port.in.like.CreateLikeUseCase;
import com.pikachu.purple.application.review.port.in.like.DeleteLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.CreateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.review.CreateReviewSimpleUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateReviewSimpleUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.review.api.ReviewApi;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewDetailRequest;
import com.pikachu.purple.bootstrap.review.dto.request.CreateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.request.UpdateReviewDetailRequest;
import com.pikachu.purple.bootstrap.review.dto.request.UpdateReviewSimpleRequest;
import com.pikachu.purple.bootstrap.review.dto.response.GetEvaluationFormFieldResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final CreateReviewSimpleUseCase createReviewSimpleUseCase;
    private final CreateReviewDetailUseCase createReviewDetailUseCase;
    private final GetEvaluationFormFieldUseCase getEvaluationFormFieldUseCase;
    private final UpdateReviewSimpleUseCase updateReviewSimpleUseCase;
    private final UpdateReviewDetailUseCase updateReviewDetailUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;
    private final CreateComplaintUseCase createComplaintUseCase;
    private final DeleteComplaintUseCase deleteComplaintUseCase;
    private final CreateLikeUseCase createLikeUseCase;
    private final DeleteLikeUseCase deleteLikeUseCase;

    @Override
    public void createSimple(CreateReviewSimpleRequest request) {
        createReviewSimpleUseCase.invoke(
            request.perfumeId(),
            request.score(),
            request.content()
        );
    }

    @Override
    public void createDetail(CreateReviewDetailRequest request) {
        createReviewDetailUseCase.invoke(
            request.perfumeId(),
            request.score(),
            request.content(),
            request.evaluationFieldVOs(),
            request.moodNames()
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
    public void updateSimple(
        Long reviewId,
        UpdateReviewSimpleRequest request
    ) {
        updateReviewSimpleUseCase.invoke(
            reviewId,
            request.score(),
            request.content()
        );
    }

    @Override
    public void updateDetail(
        Long reviewId,
        UpdateReviewDetailRequest request
    ) {
        updateReviewDetailUseCase.invoke(
            reviewId,
            request.score(),
            request.content(),
            request.evaluationFieldVOs(),
            request.moodNames()
        );
    }

    @Override
    public void delete(Long reviewId) {
        deleteReviewUseCase.invoke(reviewId);
    }

    @Override
    public void complain(Long reviewId) {
        createComplaintUseCase.invoke(reviewId);
    }

    @Override
    public void deleteComplain(Long reviewId) {
        deleteComplaintUseCase.invoke(reviewId);
    }

    @Override
    public void like(Long reviewId) {
        createLikeUseCase.invoke(reviewId);
    }

    @Override
    public void unLike(Long reviewId) {
        deleteLikeUseCase.invoke(reviewId);
    }

}
