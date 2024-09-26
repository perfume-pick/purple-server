package com.pikachu.purple.bootstrap.recommend.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.recommend.dto.response.GetPerfumesAndUserAccordsByUserResponse;
import com.pikachu.purple.bootstrap.recommend.dto.response.GetPerfumesByReviewCountsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Recommend", description = "Recommend API")
@RequestMapping(value = "/perpicks/recommends", produces = "application/json")
public interface RecommendApi {

    @Secured
    @Operation(
        summary = "어코드 기반 추천 향수 리스트 조회",
        description = "메인페이지 어코드 기반 정보 반환"
    )
    @GetMapping("/user-accords/perfumes")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumesAndUserAccordsByUserResponse> findAllPerfumeWithUserAccordsByUser();

    @Secured
    @Operation(
        summary = "리뷰 수 기반 추천 향수 리스트 조회",
        description = "메인페이지 리뷰 수 기반 정보 반환"
    )
    @GetMapping("/review-counts/perfumes")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumesByReviewCountsResponse> findAllPerfumeOrderByReviewCount();

}
