package com.pikachu.purple.bootstrap.perfume.api;

import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.perfume.dto.request.GetPerfumeByBrandsRequest;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPerfumeByBrandsResponse;
import com.pikachu.purple.bootstrap.perfume.dto.response.GetPreferenceBasedRecommendResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "perfume", description = "perfume API")
@RequestMapping(value = "/perpicks/perfumes", produces = "application/json")
public interface PerfumeApi {

    @Operation(summary = "온보딩에서 선택한 향수브랜드에 대한 제품 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    GetPerfumeByBrandsResponse getPerfumeByBrands(@RequestBody GetPerfumeByBrandsRequest request);

    @Secured
    @Operation(summary = "선호노트 기반 추천 향수 리스트 조회")
    @GetMapping("/preference-note")
    @ResponseStatus(HttpStatus.OK)
    GetPreferenceBasedRecommendResponse getPreferenceBasedRecommend();

}
