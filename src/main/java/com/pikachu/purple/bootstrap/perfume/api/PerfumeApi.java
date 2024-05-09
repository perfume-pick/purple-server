package com.pikachu.purple.bootstrap.perfume.api;

import com.pikachu.purple.bootstrap.perfume.dto.request.GetPerfumeRequest;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "perfume", description = "perfume API")
@RequestMapping(value = "/perpicks/perfume", produces = "application/json")
public interface PerfumeApi {

    @Operation(summary = "온보딩에서 선택한 향수브랜드에 대한 제품 조회")
    @ApiResponse(responseCode = "200", description = "브랜드에 대한 제품 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    GetPerfumeResponse getPerfumeByBrand(@RequestBody GetPerfumeRequest request);

}
