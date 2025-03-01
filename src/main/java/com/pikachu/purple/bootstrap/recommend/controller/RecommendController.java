package com.pikachu.purple.bootstrap.recommend.controller;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.port.in.GetRecommendedPerfumesByUserAccordsUseCase;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.recommend.api.RecommendApi;
import com.pikachu.purple.bootstrap.recommend.dto.response.GetPerfumesAndUserAccordsByUserResponse;
import com.pikachu.purple.bootstrap.recommend.dto.response.GetPerfumesByReviewCountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommendController implements RecommendApi {

    private final GetRecommendedPerfumesByUserAccordsUseCase getRecommendedPerfumesByUserAccordsUseCase;
    private final GetPerfumesUseCase getPerfumesUseCase;

    @Override
    public SuccessResponse<GetPerfumesAndUserAccordsByUserResponse> findAllPerfumeWithUserAccordsByUser() {
        Long userId = getCurrentUserAuthentication().userId();
        GetRecommendedPerfumesByUserAccordsUseCase.Result result = getRecommendedPerfumesByUserAccordsUseCase.findAll(userId);

        return SuccessResponse.of(new GetPerfumesAndUserAccordsByUserResponse(
            result.userAccords(),
            result.recommendedPerfumeDTOs()
        ));
    }

    @Override
    public SuccessResponse<GetPerfumesByReviewCountsResponse> findAllPerfumeOrderByReviewCount() {
        GetPerfumesUseCase.Result result = getPerfumesUseCase.findAllOrderByReviewCount();

        return SuccessResponse.of(
            GetPerfumesByReviewCountsResponse.of(result)
        );
    }

}
