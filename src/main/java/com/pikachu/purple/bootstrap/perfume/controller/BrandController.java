package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase;
import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase.Result;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.api.BrandApi;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetBrandsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumesByBrandNamesResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrandController implements BrandApi {

    private final GetBrandsUseCase getBrandsUseCase;

    @Override
    public SuccessResponse<GetBrandsResponse> findAll(){
        Result result = getBrandsUseCase.findAll();

        return SuccessResponse.of(
            GetBrandsResponse.of(result)
        );
    }

    @Override
    public SuccessResponse<GetPerfumesByBrandNamesResponse> findAllWithPerfumes(List<String> request) {
        Result result = getBrandsUseCase.findAllWithPerfumes(request);

        return SuccessResponse.of(
            GetPerfumesByBrandNamesResponse.of(result)
        );
    }

}
