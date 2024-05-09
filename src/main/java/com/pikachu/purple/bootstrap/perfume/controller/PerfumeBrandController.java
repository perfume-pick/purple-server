package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.PerfumeBrandGetUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeBrandGetUseCase.Result;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeBrandApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeBrandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeBrandController implements PerfumeBrandApi {

    private final PerfumeBrandGetUseCase perfumeBrandGetUseCase;

    @Override
    public GetPerfumeBrandResponse getSelectBrand(){
        Result result = perfumeBrandGetUseCase.invoke();

        return new GetPerfumeBrandResponse(result.perfumeBrandList());
    }

}
