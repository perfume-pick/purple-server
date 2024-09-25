package com.pikachu.purple.bootstrap.user.api;

import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.common.security.Secured;
import com.pikachu.purple.bootstrap.user.dto.response.GetPerfumesAndUserAccordsByUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "UserAccord", description = "UserAccord API")
@RequestMapping(value = "/perpicks/user-accords", produces = "application/json")
public interface UserAccordApi {

    @Secured
    @Operation(
        summary = "어코드 기반 추천 향수 리스트 조회",
        description = "메인페이지 어코드 기반 정보 반환"
    )
    @GetMapping("/perfumes")
    @ResponseStatus(HttpStatus.OK)
    SuccessResponse<GetPerfumesAndUserAccordsByUserResponse> findAllPerfumeWithUserAccordsByUser();

}
