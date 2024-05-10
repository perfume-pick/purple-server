package com.pikachu.purple.bootstrap.perfume.api;

import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeBrandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "perfumeBrand", description = "perfumeBrand API")
@RequestMapping(value = "/perpicks/perfumeBrands", produces = "application/json")
public interface PerfumeBrandApi {

    @Operation(summary = "온보딩에 필요한 TOP 30 브랜드 조회")
    @ApiResponse(responseCode = "200", description = "TOP 30 브랜드 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    GetPerfumeBrandResponse get();

}
