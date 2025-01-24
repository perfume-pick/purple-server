package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeDetailByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.fragranticaevaluation.GetFragranticaEvaluationByPerfumeIdUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsByPerfumeIdAndSortTypeUseCase;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeDetailResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetFragranticaEvaluationResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeStatisticResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetReviewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final GetPerfumeDetailByPerfumeIdUseCase getPerfumeDetailByPerfumeIdUseCase;
    private final GetFragranticaEvaluationByPerfumeIdUseCase getFragranticaEvaluationByPerfumeIdUseCase;
    private final GetPerfumeStatisticUseCase getPerfumeStatisticUseCase;
    private final GetReviewsByPerfumeIdAndSortTypeUseCase getReviewsByPerfumeIdAndSortTypeUseCase;

    @Override
    public SuccessResponse<GetPerfumeDetailResponse> findAccordsAndNotesByPerfumeId(Long perfumeId) {
        GetPerfumeDetailByPerfumeIdUseCase.Result result = getPerfumeDetailByPerfumeIdUseCase.invoke(perfumeId);

        return SuccessResponse.of(new GetPerfumeDetailResponse(result.perfumeDetail()));
    }

    @Override
    public SuccessResponse<GetFragranticaEvaluationResponse> findFragranticaEvaluationByPerfumeId(
        Long perfumeId) {

        GetFragranticaEvaluationByPerfumeIdUseCase.Result result = getFragranticaEvaluationByPerfumeIdUseCase.invoke(perfumeId);

        return SuccessResponse.of(
            new GetFragranticaEvaluationResponse(result.fragranticaEvaluation()));
    }

    @Override
    public SuccessResponse<GetPerfumeStatisticResponse> findPerfumeStatisticResponse(
        Long perfumeId) {
        GetPerfumeStatisticUseCase.Result result = getPerfumeStatisticUseCase.invoke(perfumeId);

        return SuccessResponse.of(
            new GetPerfumeStatisticResponse(
                result.starRatingStatistics(),
                result.evaluationStatistics()
            )
        );
    }

    @Override
    public SuccessResponse<GetReviewsResponse> findReviewsByPerfumeIdAndSortType(
        Long perfumeId,
        String sortType
    ) {
        GetReviewsByPerfumeIdAndSortTypeUseCase.Result result = getReviewsByPerfumeIdAndSortTypeUseCase.invoke(
            perfumeId,
            sortType
        );

        return SuccessResponse.of(new GetReviewsResponse(result.reviewDTOs()));
    }

}
