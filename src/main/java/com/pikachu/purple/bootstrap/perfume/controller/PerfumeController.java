package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.GetAccordsAndNotesByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.GetFragranticaEvaluationByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.GetPerfumesByKeywordUseCase;
import com.pikachu.purple.application.review.port.in.GetReviewsByPerfumeIdAndSortTypeUseCase;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticByPerfumeIdUseCase;
import com.pikachu.purple.application.user.port.in.CreateSearchHistoryUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetAccordsAndNotesResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetFragranticaEvaluationResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeStatisticResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumesResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetReviewsResponse;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final GetPerfumesByKeywordUseCase getPerfumesByKeywordUseCase;
    private final GetAccordsAndNotesByPerfumeIdUseCase getAccordsAndNotesByPerfumeIdUseCase;
    private final GetFragranticaEvaluationByPerfumeIdUseCase getFragranticaEvaluationByPerfumeIdUseCase;
    private final CreateSearchHistoryUseCase createSearchHistoryUseCase;
    private final GetPerfumeStatisticByPerfumeIdUseCase getPerfumeStatisticByPerfumeIdUseCase;
    private final GetReviewsByPerfumeIdAndSortTypeUseCase getReviewsByPerfumeIdAndSortTypeUseCase;

    @Override
    public SuccessResponse<GetPerfumesResponse> findAllByKeyword(String keyword) {
        GetPerfumesByKeywordUseCase.Result result = getPerfumesByKeywordUseCase.invoke(
            new GetPerfumesByKeywordUseCase.Command(keyword));

        Instant searchAt = Instant.now();
        createSearchHistoryUseCase.invoke(
            keyword,
            searchAt
        );

        return SuccessResponse.of(new GetPerfumesResponse(result.perfumeDTOs()));
    }

    @Override
    public SuccessResponse<GetAccordsAndNotesResponse> findAccordsAndNotesByPerfumeId(Long perfumeId) {
        GetAccordsAndNotesByPerfumeIdUseCase.Result result = getAccordsAndNotesByPerfumeIdUseCase.invoke(
            new GetAccordsAndNotesByPerfumeIdUseCase.Command(perfumeId));

        // TODO: userSaveVisitedHistoryUseCase 구현

        return SuccessResponse.of(new GetAccordsAndNotesResponse(
            result.accords(),
            result.notes()
        ));
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
        GetPerfumeStatisticByPerfumeIdUseCase.Result result = getPerfumeStatisticByPerfumeIdUseCase.invoke(
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
