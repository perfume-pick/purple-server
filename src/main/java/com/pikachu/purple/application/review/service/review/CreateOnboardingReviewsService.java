package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.review.CreateOnboardingReviewsUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOnboardingReviewsService implements CreateOnboardingReviewsUseCase {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public void createAll(List<StarRating> starRatings) {
        List<Review> reviews = new ArrayList<>();

        for (StarRating starRating : starRatings) {
            Long reviewId = IdUtil.generateId();

            Review review = Review.builder()
                .id(reviewId)
                .type(ReviewType.ONBOARDING)
                .starRating(starRating)
                .user(
                    User.builder()
                        .id(starRating.getUser().getId())
                        .build()
                )
                .perfume(
                    Perfume.builder()
                        .id(starRating.getPerfume().getId())
                        .build()
                )
                .build();

            reviews.add(review);
        }

        reviewRepository.createAll(reviews);
    }

}
