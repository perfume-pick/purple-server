package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.GetAccordsAndNotesByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.fragranticaevaluation.GetFragranticaEvaluationByPerfumeIdUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsByPerfumeIdAndSortTypeUseCase;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticByPerfumeIdUseCase;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetAccordsAndNotesResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetFragranticaEvaluationResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeStatisticResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetReviewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final GetAccordsAndNotesByPerfumeIdUseCase getAccordsAndNotesByPerfumeIdUseCase;
    private final GetFragranticaEvaluationByPerfumeIdUseCase getFragranticaEvaluationByPerfumeIdUseCase;
    private final GetPerfumeStatisticByPerfumeIdUseCase getPerfumeStatisticByPerfumeIdUseCase;
    private final GetReviewsByPerfumeIdAndSortTypeUseCase getReviewsByPerfumeIdAndSortTypeUseCase;

    @Override
    public SuccessResponse<GetAccordsAndNotesResponse> findAccordsAndNotesByPerfumeId(String perfumeId) {
        GetAccordsAndNotesByPerfumeIdUseCase.Result result = getAccordsAndNotesByPerfumeIdUseCase.invoke(
            new GetAccordsAndNotesByPerfumeIdUseCase.Command(IdUtil.from(perfumeId)));

        // TODO: userSaveVisitedHistoryUseCase 구현

        return SuccessResponse.of(new GetAccordsAndNotesResponse(
            result.accords(),
            result.notes()
        ));
    }

    @Override
    public SuccessResponse<GetFragranticaEvaluationResponse> findFragranticaEvaluationByPerfumeId(
        String perfumeId) {

        GetFragranticaEvaluationByPerfumeIdUseCase.Result result = getFragranticaEvaluationByPerfumeIdUseCase.invoke(
            new GetFragranticaEvaluationByPerfumeIdUseCase.Command(IdUtil.from(perfumeId)));

        return SuccessResponse.of(
            new GetFragranticaEvaluationResponse(result.fragranticaEvaluation()));
    }

    @Override
    public SuccessResponse<GetPerfumeStatisticResponse> findPerfumeStatisticResponse(
        String perfumeId) {
        GetPerfumeStatisticByPerfumeIdUseCase.Result result = getPerfumeStatisticByPerfumeIdUseCase.invoke(
            new GetPerfumeStatisticByPerfumeIdUseCase.Command(IdUtil.from(perfumeId)));

        return SuccessResponse.of(
            new GetPerfumeStatisticResponse(
                result.starRatingStatistics(),
                result.evaluationStatistics()
            )
        );
    }

    @Override
    public SuccessResponse<GetReviewsResponse> findReviewsByPerfumeIdAndSortType(
        String perfumeId,
        String sortType
    ) {
        GetReviewsByPerfumeIdAndSortTypeUseCase.Result result = getReviewsByPerfumeIdAndSortTypeUseCase.invoke(
            new GetReviewsByPerfumeIdAndSortTypeUseCase.Command(
                IdUtil.from(perfumeId),
                sortType
            )
        );

        return SuccessResponse.of(new GetReviewsResponse(result.reviewDTOs()));
    }

}
