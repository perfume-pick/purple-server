package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.GetCurrentAndAverageUserReviewCountsUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.user.port.in.user.GetUserCountsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetCurrentAndAverageUserReviewCountsService implements
    GetCurrentAndAverageUserReviewCountsUseCase {

    private final ReviewRepository reviewRepository;
    private final GetUserCountsUseCase getUserCountsUseCase;

    @Override
    public Result find(Long userId) {
        int currentUserReviewCounts = reviewRepository.count(userId);
        int totalReviewCounts = reviewRepository.count();
        int userCounts = getUserCountsUseCase.count().userCounts();
        double averageUserReviewCounts = Math.round((double) totalReviewCounts / userCounts);

        return new Result(
            currentUserReviewCounts,
            (int) averageUserReviewCounts
        );
    }

}
