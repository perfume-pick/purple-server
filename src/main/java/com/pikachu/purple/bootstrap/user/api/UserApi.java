package com.pikachu.purple.bootstrap.user.api;

import com.pikachu.purple.bootstrap.user.dto.request.RatingRequest;
import com.pikachu.purple.bootstrap.user.dto.request.UpdateNicknameRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "User", description = "User API")
@RequestMapping(value = "/perpicks/users", produces = "application/json")
public interface UserApi {

    @Operation(summary = "온보딩에서 향수에 대한 별점 저장")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void saveRating(@RequestBody RatingRequest request);

    @Operation(summary = "닉네임 변경")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204", description = "닉네임 변경 성공"
            ),
            @ApiResponse(
                responseCode = "404", description = "U003: 사용자를 찾을 수 없습니다."
            ),
            @ApiResponse(
                responseCode = "409", description = "U004: 이미 사용중인 닉네임입니다."
            )
        }
    )
    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateNickname(@RequestBody UpdateNicknameRequest request);

}
