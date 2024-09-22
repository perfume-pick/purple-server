package com.pikachu.purple.bootstrap.search.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Search", description = "Search API")
@RequestMapping(value = "/perpicks/search", produces = "application/json")
public interface SearchApi {

    @Secured
    @Operation(
        summary = "향수 검색",
        description = "메인페이지 검색 정보 반환"
    )
    @GetMapping("/perfumes")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumesResponse> findAllPerfumesByKeyword(@RequestParam String keyword);

}
