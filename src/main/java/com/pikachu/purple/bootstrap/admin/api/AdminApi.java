package com.pikachu.purple.bootstrap.admin.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Admin", description = "Admin API")
@RequestMapping(value = "/perpicks/admin", produces = "application/json")
public interface AdminApi {

    @Operation(
        summary = "별점 집계",
        description = "수동 별점 집계 API"
    )
    @PostMapping("/star-rating-statistics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateStarRatingStatistics();

    @Operation(
        summary = "향수 평점 전체 갱신",
        description = "향수 평점 전체 수동 갱신 API"
    )
    @PostMapping("/perfume-average-scores")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updatePerfumeAverageScores();

    @Operation(
        summary = "평가 집계",
        description = "수동 평가 집계 API"
    )
    @PostMapping("/evaluation-statistics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void countEvaluationStatistics();


}
