package com.pikachu.purple.bootstrap.user.controller;

import com.pikachu.purple.application.perfume.port.in.GetPerfumesAndUserAccordsByUserUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.user.api.UserAccordApi;
import com.pikachu.purple.bootstrap.user.dto.response.GetPerfumesAndUserAccordsByUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAccordController implements UserAccordApi {

    private final GetPerfumesAndUserAccordsByUserUseCase getPerfumesAndUserAccordsByUserUseCase;

    @Override
    public SuccessResponse<GetPerfumesAndUserAccordsByUserResponse> findAllWithUserAccordsByUser() {
        GetPerfumesAndUserAccordsByUserUseCase.Result result = getPerfumesAndUserAccordsByUserUseCase.invoke();

        return SuccessResponse.of(new GetPerfumesAndUserAccordsByUserResponse(
            result.userAccords(),
            result.recommendedPerfumeDTOs()
        ));
    }

}
