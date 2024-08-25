package com.pikachu.purple.bootstrap.review.api;

import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.review.dto.request.ReviewCreateRequest;
import com.pikachu.purple.bootstrap.review.dto.request.ReviewUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "review", description = "review API")
@RequestMapping(value = "/perpicks/reviews", produces = "application/json")
public interface ReviewApi {

    @Secured
    @Operation(summary = "향수 평가시 간단한 리뷰 작성")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody @Valid ReviewCreateRequest request);

    @Secured
    @Operation(summary = "자신이 작성한 리뷰 내용 및 별점 수정")
    @PatchMapping("/{review-id}/content")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(
        @PathVariable("review-id") Long reviewId,
        @RequestBody @Valid ReviewUpdateRequest request
    );

    @Secured
    @Operation(summary = "자신이 작성한 리뷰 삭제")
    @DeleteMapping("/{review-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
        @PathVariable("review-id") Long reviewId
    );

}
