package com.pikachu.purple.bootstrap.perfume.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByBrandsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByKeywordResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeDetailResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPreferenceBasedRecommendResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Perfume", description = "Perfume API")
@RequestMapping(value = "/perpicks/perfumes", produces = "application/json")
public interface PerfumeApi {

    @Operation(summary = "온보딩에서 선택한 향수브랜드에 대한 제품 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumeByBrandsResponse> getPerfumeByBrands(
        @RequestParam List<String> request);

    @Secured
    @Operation(summary = "선호노트 기반 추천 향수 리스트 조회")
    @GetMapping("/preference")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPreferenceBasedRecommendResponse> getAllPerfumeByPreference();

    @Secured
    @Operation(summary = "메인페이지에서 향수 또는 향수 브랜드 검색")
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumeByKeywordResponse> findByKeywords(@RequestParam String keyword);

    @Secured
    @Operation(summary = "향수 상세 정보 조회")
    @GetMapping("/{perfume-id}/detail")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumeDetailResponse> findPerfumeDetailByPerfumeId(
        @PathVariable("perfume-id") Long perfumeId);

}
