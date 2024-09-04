package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.evaluation.port.in.FragranticaEvaluationGetByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeDetailGetByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByKeywordUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByUserPreferenceNoteUseCase;
import com.pikachu.purple.application.user.port.in.UserSaveSearchHistoryUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetFragranticaEvaluationsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByBrandsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByKeywordResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeDetailResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPreferenceBasedRecommendResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final PerfumeGetByBrandsUseCase perfumeGetByBrandsUseCase;
    private final PerfumeGetByUserPreferenceNoteUseCase perfumeGetByUserPreferenceNoteUseCase;
    private final PerfumeGetByKeywordUseCase perfumeGetByKeywordUseCase;
    private final PerfumeDetailGetByPerfumeIdUseCase perfumeDetailGetByPerfumeIdUseCase;
    private final FragranticaEvaluationGetByPerfumeIdUseCase fragranticaEvaluationGetByPerfumeIdUseCase;
    private final UserSaveSearchHistoryUseCase userSaveSearchHistoryUseCase;

    @Override
    public SuccessResponse<GetPerfumeByBrandsResponse> getPerfumeByBrands(List<String> request) {
        PerfumeGetByBrandsUseCase.Result result = perfumeGetByBrandsUseCase.invoke(
            new PerfumeGetByBrandsUseCase.Command(request));

        return SuccessResponse.of(new GetPerfumeByBrandsResponse(result.perfumes()));
    }

    @Override
    public SuccessResponse<GetPreferenceBasedRecommendResponse> getAllPerfumeByPreference() {
        PerfumeGetByUserPreferenceNoteUseCase.Result result = perfumeGetByUserPreferenceNoteUseCase.invoke();

        return SuccessResponse.of(new GetPreferenceBasedRecommendResponse(
            result.userPreferenceNotes(),
            result.perfumes()
        ));
    }

    @Override
    public SuccessResponse<GetPerfumeByKeywordResponse> findByKeywords(String keyword) {
        PerfumeGetByKeywordUseCase.Result result = perfumeGetByKeywordUseCase.invoke(
            new PerfumeGetByKeywordUseCase.Command(keyword));

        LocalDateTime searchAt = LocalDateTime.now();
        userSaveSearchHistoryUseCase.invoke(
            keyword,
            searchAt
        );

        return SuccessResponse.of(new GetPerfumeByKeywordResponse(result.perfumes()));
    }

    @Override
    public SuccessResponse<GetPerfumeDetailResponse> findPerfumeDetailByPerfumeId(Long perfumeId) {
        PerfumeDetailGetByPerfumeIdUseCase.Result result = perfumeDetailGetByPerfumeIdUseCase.invoke(
            new PerfumeDetailGetByPerfumeIdUseCase.Command(perfumeId));

        // TODO: userSaveVisitedHistoryUseCase 구현

        return SuccessResponse.of(new GetPerfumeDetailResponse(result.perfumeDetail()));
    }

    @Override
    public SuccessResponse<GetFragranticaEvaluationsResponse> findFragranticaEvaluationsByPerfumeId(
        Long perfumeId) {

        FragranticaEvaluationGetByPerfumeIdUseCase.Result result = fragranticaEvaluationGetByPerfumeIdUseCase.invoke(
            new FragranticaEvaluationGetByPerfumeIdUseCase.Command(perfumeId));

        return SuccessResponse.of(new GetFragranticaEvaluationsResponse(result.fragranticaEvaluations()));
    }

}
