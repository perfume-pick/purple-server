package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase.Command;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetByBrandsUseCase.Result;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.request.GetPerfumeByBrandsRequest;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByBrandsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final PerfumeGetByBrandsUseCase perfumeGetByBrandsUseCase;

    @Override
    public GetPerfumeByBrandsResponse getPerfumeByBrands(GetPerfumeByBrandsRequest request) {
        Result result = perfumeGetByBrandsUseCase.invoke(new Command(request.brandList()));

        return new GetPerfumeByBrandsResponse(result.perfumeList());
    }

}
