package com.pikachu.purple.bootstrap.review.controller;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase;
import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase.Result;
import com.pikachu.purple.application.review.port.in.complaint.CreateComplaintUseCase;
import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintUseCase;
import com.pikachu.purple.application.review.port.in.like.CreateLikeUseCase;
import com.pikachu.purple.application.review.port.in.like.DeleteLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.CreateDetailedReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.CreateSimpleReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateDetailedReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateSimpleReviewUseCase;
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

    private final CreateSimpleReviewUseCase createSimpleReviewUseCase;
    private final CreateDetailedReviewUseCase createDetailedReviewUseCase;
    private final GetEvaluationFormFieldUseCase getEvaluationFormFieldUseCase;
    private final UpdateSimpleReviewUseCase updateSimpleReviewUseCase;
    private final UpdateDetailedReviewUseCase updateDetailedReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;
    private final CreateComplaintUseCase createComplaintUseCase;
    private final DeleteComplaintUseCase deleteComplaintUseCase;
    private final CreateLikeUseCase createLikeUseCase;
    private final DeleteLikeUseCase deleteLikeUseCase;

    @Override
    public void createSimple(CreateReviewSimpleRequest request) {
        Long userId = getCurrentUserAuthentication().userId();

        createSimpleReviewUseCase.create(
            userId,
            request.perfumeId(),
            request.score(),
            request.content()
        );
    }

    @Override
    public void createDetail(CreateReviewDetailRequest request) {
        Long userId = getCurrentUserAuthentication().userId();

        createDetailedReviewUseCase.create(
            userId,
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
            GetEvaluationFormFieldResponse.of(result)
        );
    }

    @Override
    public void updateSimple(
        Long reviewId,
        UpdateReviewSimpleRequest request
    ) {
        updateSimpleReviewUseCase.update(
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
        updateDetailedReviewUseCase.update(
            reviewId,
            request.score(),
            request.content(),
            request.evaluationFieldVOs(),
            request.moodNames()
        );
    }

    @Override
    public void delete(Long reviewId) {
        deleteReviewUseCase.delete(reviewId);
    }

    @Override
    public void complain(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        createComplaintUseCase.create(
            userId,
            reviewId
        );
    }

    @Override
    public void deleteComplain(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        deleteComplaintUseCase.delete(
            userId,
            reviewId
        );
    }

    @Override
    public void like(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        createLikeUseCase.create(
            userId,
            reviewId
        );
    }

    @Override
    public void unLike(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        deleteLikeUseCase.delete(
            userId,
            reviewId
        );
    }

}
