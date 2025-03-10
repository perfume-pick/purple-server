package com.pikachu.purple.bootstrap.perfume.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeDetailResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetFragranticaEvaluationResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeStatisticResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetReviewsByPerfumeIdAndSortTypeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Perfume", description = "Perfume API")
@RequestMapping(value = "/perpicks/perfumes", produces = "application/json")
public interface PerfumeApi {

    @Secured
    @Operation(
        summary = "향수 상세 정보 조회",
        description = "향수 기본 정보, 메인 어코드, Top/Middle/Base 노트 정보를 제공합니다."
    )
    @GetMapping("/{perfume-id}/detail")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumeDetailResponse> findAccordsAndNotesByPerfumeId(
        @PathVariable("perfume-id") @NotNull @Positive Long perfumeId);

    @Secured
    @Operation(summary = "프라그란티카 평가 정보 조회")
    @GetMapping("/{perfume-id}/fragrantica-evaluation")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetFragranticaEvaluationResponse> findFragranticaEvaluationByPerfumeId(
        @PathVariable("perfume-id") @NotNull @Positive Long perfumeId);

    @Secured
    @Operation(summary = "코멘트 토픽 조회")
    @GetMapping("/{perfume-id}/statistics")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumeStatisticResponse> findPerfumeStatisticResponse(
        @PathVariable("perfume-id") @NotNull @Positive Long perfumeId);

    @Secured
    @Operation(
        summary = "리뷰 전체 조회",
        description = "향수 상세 페이지, 향수에 대한 모든 리뷰 정보 반환"
    )
    @GetMapping("/{perfume-id}/reviews")
    SuccessResponse<GetReviewsByPerfumeIdAndSortTypeResponse> findReviewsByPerfumeIdAndSortType(
        @PathVariable("perfume-id") @NotNull @Positive Long perfumeId,
        @RequestParam("sort-type") @NotEmpty String sortType
    );

}
