package com.pikachu.purple.bootstrap.recommend.controller;

import com.pikachu.purple.application.perfume.port.in.GetCurrentUserPerfumesWithUserAccordsUseCase;
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

    private final GetCurrentUserPerfumesWithUserAccordsUseCase getCurrentUserPerfumesWithUserAccordsUseCase;
    private final GetPerfumesUseCase getPerfumesUseCase;

    @Override
    public SuccessResponse<GetPerfumesAndUserAccordsByUserResponse> findAllPerfumeWithUserAccordsByUser() {
        GetCurrentUserPerfumesWithUserAccordsUseCase.Result result = getCurrentUserPerfumesWithUserAccordsUseCase.findAll();

        return SuccessResponse.of(new GetPerfumesAndUserAccordsByUserResponse(
            result.userAccords(),
            result.recommendedPerfumeDTOs()
        ));
    }

    @Override
    public SuccessResponse<GetPerfumesByReviewCountsResponse> findAllPerfumeOrderByReviewCount() {
        GetPerfumesUseCase.ResultRecommendedPerfumeDTO result = getPerfumesUseCase.findAllOrderByReviewCount();

        return SuccessResponse.of(new GetPerfumesByReviewCountsResponse(
            result.perfumeDTOs()
        ));
    }

}
