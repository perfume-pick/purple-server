package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.PerfumeBrandGetTopThirtyUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeBrandGetTopThirtyUseCase.Result;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeBrandApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetTopThirtyPerfumeBrandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeBrandController implements PerfumeBrandApi {

    private final PerfumeBrandGetTopThirtyUseCase perfumeBrandGetTopThirtyUseCase;

    @Override
    public SuccessResponse<GetTopThirtyPerfumeBrandResponse> getTopThirtyBrands(){
        Result result = perfumeBrandGetTopThirtyUseCase.invoke();

        return SuccessResponse.of(new GetTopThirtyPerfumeBrandResponse(result.perfumeBrandList()));
    }

}
