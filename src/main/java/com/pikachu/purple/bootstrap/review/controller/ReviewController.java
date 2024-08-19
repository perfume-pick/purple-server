package com.pikachu.purple.bootstrap.review.controller;

import com.pikachu.purple.application.review.port.in.ReviewCreateUseCase;
import com.pikachu.purple.application.review.port.in.ReviewCreateUseCase.OnboardingCommand;
import com.pikachu.purple.application.review.port.in.ReviewDeleteUseCase;
import com.pikachu.purple.application.review.port.in.ReviewUpdateUseCase;
import com.pikachu.purple.bootstrap.review.api.ReviewApi;
import com.pikachu.purple.bootstrap.review.dto.request.ReviewCreateOnboardingRequest;
import com.pikachu.purple.bootstrap.review.dto.request.ReviewCreateRequest;
import com.pikachu.purple.bootstrap.review.dto.request.ReviewUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewCreateUseCase reviewCreateUseCase;
    private final ReviewUpdateUseCase reviewUpdateUseCase;
    private final ReviewDeleteUseCase reviewDeleteUseCase;

    @Override
    public void createOnboarding(ReviewCreateOnboardingRequest request) {
        reviewCreateUseCase.createOnboarding(
            new OnboardingCommand(request.ratingValues())
        );
    }

    @Override
    public void create(ReviewCreateRequest request) {
        reviewCreateUseCase.create(
            new ReviewCreateUseCase.Command(
                request.perfumeId(),
                request.score(),
                request.content()
            )
        );

    }

    @Override
    public void update(
        Long reviewId,
        ReviewUpdateRequest request
    ) {
        reviewUpdateUseCase.invoke(
            new ReviewUpdateUseCase.Command(
                reviewId,
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
