package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.GetCurrentAndAverageUserReviewCountsUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.user.port.in.user.GetUserCountsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetCurrentAndAverageUserReviewCountsService implements
    GetCurrentAndAverageUserReviewCountsUseCase {

    private final ReviewDomainService reviewDomainService;
    private final GetUserCountsUseCase getUserCountsUseCase;

    @Override
    public Result find(Long userId) {
        int currentUserReviewCounts = reviewDomainService.count(userId);
        int totalReviewCounts = reviewDomainService.count();
        int userCounts = getUserCountsUseCase.invoke().userCounts();
        double averageUserReviewCounts = Math.round((double) totalReviewCounts / userCounts);

        return new Result(
            currentUserReviewCounts,
            (int) averageUserReviewCounts
        );
    }

}
