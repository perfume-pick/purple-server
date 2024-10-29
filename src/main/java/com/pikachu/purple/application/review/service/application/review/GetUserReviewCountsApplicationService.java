package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.common.dto.UserReviewCountsDTO;
import com.pikachu.purple.application.review.port.in.review.GetUserReviewCountsUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.user.port.in.user.GetUserCountsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserReviewCountsApplicationService implements GetUserReviewCountsUseCase {

    private final ReviewDomainService reviewDomainService;
    private final GetUserCountsUseCase getUserCountsUseCase;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        int currentUserReviewCounts = reviewDomainService.count(userId);
        int totalReviewCounts = reviewDomainService.count();
        int userCounts = getUserCountsUseCase.invoke().userCounts();
        double averageUserReviewCounts = Math.round((double) totalReviewCounts / userCounts);

        UserReviewCountsDTO userReviewCountsDTO = UserReviewCountsDTO.builder()
            .currentUserReviewCounts(currentUserReviewCounts)
            .averageUserReviewCounts((int) averageUserReviewCounts)
            .build();

        return new Result(userReviewCountsDTO);
    }

}
