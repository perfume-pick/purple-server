package com.pikachu.purple.bootstrap.search.controller;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase.Result;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumesResponse;
import com.pikachu.purple.bootstrap.search.api.SearchApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController implements SearchApi {

    private final GetPerfumesUseCase getPerfumesUseCase;

    @Override
    public SuccessResponse<GetPerfumesResponse> findAllPerfumesByKeyword(String keyword) {
        Result result = getPerfumesUseCase.invoke(keyword);

        //TODO -> List<PerfumeDTO>
        return SuccessResponse.of(new GetPerfumesResponse(result.perfumes()));
    }

}
