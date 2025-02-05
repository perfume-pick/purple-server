package com.pikachu.purple.bootstrap.perfume.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetBrandsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumesByBrandNamesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Brand", description = "Brand API")
@RequestMapping(value = "/perpicks/brands", produces = "application/json")
public interface BrandApi {

    @Operation(
        summary = "브랜드 조회",
        description = "온보딩 step1에 필요한 브랜드 정보 반환"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetBrandsResponse> findAll();

    @Operation(
        summary = "브랜드별 향수 목록 조회",
        description = "온보딩 step2에 필요한 향수 정보 반환"
    )
    @GetMapping("/perfumes")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumesByBrandNamesResponse> findAllWithPerfumes(
        @RequestParam @NotEmpty List<String> request);

}
