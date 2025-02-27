package com.pikachu.purple.bootstrap.perfume.controller;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.port.in.fragranticaevaluation.GetFragranticaEvaluationUseCase;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeDetailUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeDetailResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetFragranticaEvaluationResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeStatisticResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetReviewsByPerfumeIdAndSortTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final GetFragranticaEvaluationUseCase getFragranticaEvaluationUseCase;
    private final GetPerfumeDetailUseCase getPerfumeDetailUseCase;
    private final GetPerfumeStatisticUseCase getPerfumeStatisticUseCase;
    private final GetReviewsUseCase getReviewsUseCase;

    @Override
    public SuccessResponse<GetPerfumeDetailResponse> findAccordsAndNotesByPerfumeId(Long perfumeId) {
        GetPerfumeDetailUseCase.Result result = getPerfumeDetailUseCase.find(perfumeId);

        return SuccessResponse.of(new GetPerfumeDetailResponse(result.data()));
    }

    @Override
    public SuccessResponse<GetFragranticaEvaluationResponse> findFragranticaEvaluationByPerfumeId(
        Long perfumeId) {
        GetFragranticaEvaluationUseCase.Result result = getFragranticaEvaluationUseCase.find(perfumeId);

        return SuccessResponse.of(
            new GetFragranticaEvaluationResponse(result.data()));
    }

    @Override
    public SuccessResponse<GetPerfumeStatisticResponse> findPerfumeStatisticResponse(
        Long perfumeId) {
        GetPerfumeStatisticUseCase.Result result = getPerfumeStatisticUseCase.find(perfumeId);

        return SuccessResponse.of(
            GetPerfumeStatisticResponse.of(result)
        );
    }

    @Override
    public SuccessResponse<GetReviewsByPerfumeIdAndSortTypeResponse> findReviewsByPerfumeIdAndSortType(
        Long perfumeId,
        String sortType
    ) {
        Long userId = getCurrentUserAuthentication().userId();

        GetReviewsUseCase.Result result = getReviewsUseCase.findAll(
            userId,
            perfumeId,
            sortType
        );

        return SuccessResponse.of(GetReviewsByPerfumeIdAndSortTypeResponse.of(userId, result));
    }

}
