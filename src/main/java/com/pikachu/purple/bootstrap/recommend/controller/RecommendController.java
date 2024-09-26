package com.pikachu.purple.bootstrap.recommend.controller;

import com.pikachu.purple.application.perfume.port.in.GetPerfumesAndUserAccordsByUserUseCase;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByReviewCountUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.recommend.api.RecommendApi;
import com.pikachu.purple.bootstrap.recommend.dto.response.GetPerfumesAndUserAccordsByUserResponse;
import com.pikachu.purple.bootstrap.recommend.dto.response.GetPerfumesByReviewCountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommendController implements RecommendApi {

    private final GetPerfumesAndUserAccordsByUserUseCase getPerfumesAndUserAccordsByUserUseCase;
    private final GetPerfumesByReviewCountUseCase getPerfumesByReviewCountUseCase;

    @Override
    public SuccessResponse<GetPerfumesAndUserAccordsByUserResponse> findAllPerfumeWithUserAccordsByUser() {
        GetPerfumesAndUserAccordsByUserUseCase.Result result = getPerfumesAndUserAccordsByUserUseCase.invoke();

        return SuccessResponse.of(new GetPerfumesAndUserAccordsByUserResponse(
            result.userAccords(),
            result.recommendedPerfumeDTOs()
        ));
    }

    @Override
    public SuccessResponse<GetPerfumesByReviewCountsResponse> findAllPerfumeOrderByReviewCount() {
        GetPerfumesByReviewCountUseCase.Result result = getPerfumesByReviewCountUseCase.invoke();

        return SuccessResponse.of(new GetPerfumesByReviewCountsResponse(
            result.perfumeDTOs()
        ));
    }

}
