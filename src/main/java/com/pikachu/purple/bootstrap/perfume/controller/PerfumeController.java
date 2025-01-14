package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeDetailByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.fragranticaevaluation.GetFragranticaEvaluationByPerfumeIdUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsByPerfumeIdAndSortTypeUseCase;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticByPerfumeIdUseCase;
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
    private final GetPerfumeStatisticByPerfumeIdUseCase getPerfumeStatisticByPerfumeIdUseCase;
    private final GetReviewsByPerfumeIdAndSortTypeUseCase getReviewsByPerfumeIdAndSortTypeUseCase;

    @Override
    public SuccessResponse<GetPerfumeDetailResponse> findAccordsAndNotesByPerfumeId(Long perfumeId) {
        GetPerfumeDetailByPerfumeIdUseCase.Result result = getPerfumeDetailByPerfumeIdUseCase.invoke(
            new GetPerfumeDetailByPerfumeIdUseCase.Command(perfumeId));

        return SuccessResponse.of(new GetPerfumeDetailResponse(result.perfumeDetail()));
    }

    @Override
    public SuccessResponse<GetFragranticaEvaluationResponse> findFragranticaEvaluationByPerfumeId(
        Long perfumeId) {

        GetFragranticaEvaluationByPerfumeIdUseCase.Result result = getFragranticaEvaluationByPerfumeIdUseCase.invoke(
            new GetFragranticaEvaluationByPerfumeIdUseCase.Command(perfumeId));

        return SuccessResponse.of(
            new GetFragranticaEvaluationResponse(result.fragranticaEvaluation()));
    }

    @Override
    public SuccessResponse<GetPerfumeStatisticResponse> findPerfumeStatisticResponse(
        Long perfumeId) {
        GetPerfumeStatisticByPerfumeIdUseCase.Result result = getPerfumeStatisticByPerfumeIdUseCase. invoke(
            new GetPerfumeStatisticByPerfumeIdUseCase.Command(perfumeId));

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
            new GetReviewsByPerfumeIdAndSortTypeUseCase.Command(
                perfumeId,
                sortType
            )
        );

        return SuccessResponse.of(new GetReviewsResponse(result.reviewDTOs()));
    }

}
