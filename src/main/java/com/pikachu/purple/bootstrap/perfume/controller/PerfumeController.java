package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase.Command;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByUserPreferenceNoteUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByBrandsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPreferenceBasedRecommendResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final PerfumeGetByBrandsUseCase perfumeGetByBrandsUseCase;
    private final PerfumeGetByUserPreferenceNoteUseCase perfumeGetByUserPreferenceNoteUseCase;

    @Override
    public SuccessResponse<GetPerfumeByBrandsResponse> getPerfumeByBrands(List<String> request) {
        PerfumeGetByBrandsUseCase.Result result = perfumeGetByBrandsUseCase.invoke(new Command(request));

        return SuccessResponse.of(new GetPerfumeByBrandsResponse(result.perfumeList())) ;
    }

    @Override
    public SuccessResponse<GetPreferenceBasedRecommendResponse> getAllPerfumeByPreference() {
        PerfumeGetByUserPreferenceNoteUseCase.Result result = perfumeGetByUserPreferenceNoteUseCase.invoke();

        return SuccessResponse.of(new GetPreferenceBasedRecommendResponse(
            result.userPreferenceNoteList(),
            result.perfumeList()
        ));
    }

}
