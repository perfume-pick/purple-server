package com.pikachu.purple.bootstrap.perfume.controller;

import com.pikachu.purple.application.perfume.port.in.PerfumeGetUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetUseCase.Command;
import com.pikachu.purple.application.perfume.port.in.PerfumeGetUseCase.Result;
import com.pikachu.purple.bootstrap.perfume.api.PerfumeApi;
import com.pikachu.purple.bootstrap.perfume.dto.request.GetPerfumeRequest;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerfumeController implements PerfumeApi {

    private final PerfumeGetUseCase perfumeGetUseCase;

    @Override
    public GetPerfumeResponse getPerfumeByBrand(GetPerfumeRequest request) {
        Result result = perfumeGetUseCase.invoke(new Command(request.brandList()));

        return new GetPerfumeResponse(result.perfumeList());
    }

}
