package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.GetFragranticaEvaluationByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.GetAccordsAndNotesByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByKeywordUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByUserPreferenceNoteUseCase;
import com.pikachu.purple.application.user.port.in.UserSaveSearchHistoryUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetFragranticaEvaluationResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByBrandsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByKeywordResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetAccordsAndNotesResponse;
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
    private final GetAccordsAndNotesByPerfumeIdUseCase getAccordsAndNotesByPerfumeIdUseCase;
    private final GetFragranticaEvaluationByPerfumeIdUseCase getFragranticaEvaluationByPerfumeIdUseCase;
    private final UserSaveSearchHistoryUseCase userSaveSearchHistoryUseCase;

    @Override
    public SuccessResponse<GetPerfumeByBrandsResponse> findAllByPerfumeBrands(List<String> request) {
        PerfumeGetByBrandsUseCase.Result result = perfumeGetByBrandsUseCase.invoke(
            new PerfumeGetByBrandsUseCase.Command(request));

        return SuccessResponse.of(new GetPerfumeByBrandsResponse(result.brandPerfumesDTOs()));
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
    public SuccessResponse<GetAccordsAndNotesResponse> findAccordsAndNotesByPerfumeId(Long perfumeId) {
        GetAccordsAndNotesByPerfumeIdUseCase.Result result = getAccordsAndNotesByPerfumeIdUseCase.invoke(
            new GetAccordsAndNotesByPerfumeIdUseCase.Command(perfumeId));

        // TODO: userSaveVisitedHistoryUseCase 구현

        return SuccessResponse.of(new GetAccordsAndNotesResponse(result.perfumeDetail()));
    }

    @Override
    public SuccessResponse<GetFragranticaEvaluationResponse> findFragranticaEvaluationByPerfumeId(
        Long perfumeId) {

        GetFragranticaEvaluationByPerfumeIdUseCase.Result result = getFragranticaEvaluationByPerfumeIdUseCase.invoke(
            new GetFragranticaEvaluationByPerfumeIdUseCase.Command(perfumeId));

        return SuccessResponse.of(
            new GetFragranticaEvaluationResponse(result.fragranticaEvaluation()));
    }

}
