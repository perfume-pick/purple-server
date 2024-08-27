package com.pikachu.purple.bootstrap.rating.api;

import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.rating.dto.request.RatingCreateOnboardingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Rating", description = "Rating API")
@RequestMapping(value = "/perpicks/ratings", produces = "application/json")
public interface RatingApi {

    @Secured
    @Operation(summary = "온보딩에서 향수 평가 저장")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204", description = "성공 시 상태코드(204)만 반환"
            )
        }
    )
    @PostMapping("/onboarding")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createOnboarding(@RequestBody @Valid RatingCreateOnboardingRequest request);

}
