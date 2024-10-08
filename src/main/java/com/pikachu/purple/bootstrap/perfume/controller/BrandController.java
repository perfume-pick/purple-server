package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase;
import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase.Result;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesByBrandsUseCase;
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
    private final GetPerfumesByBrandsUseCase getPerfumesByBrandsUseCase;

    @Override
    public SuccessResponse<GetBrandsResponse> findAll(){
        Result result = getBrandsUseCase.invoke();

        return SuccessResponse.of(new GetBrandsResponse(result.brandDTOs()));
    }

    @Override
    public SuccessResponse<GetPerfumesByBrandNamesResponse> findAllPerfumesByBrandNames(List<String> request) {
        GetPerfumesByBrandsUseCase.Result result = getPerfumesByBrandsUseCase.invoke(
            new GetPerfumesByBrandsUseCase.Command(request));

        return SuccessResponse.of(new GetPerfumesByBrandNamesResponse(result.brandPerfumesDTOs()));
    }

}
