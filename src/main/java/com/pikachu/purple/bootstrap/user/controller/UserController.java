package com.pikachu.purple.bootstrap.user.controller;

import com.pikachu.purple.application.rating.port.in.RatingSaveUseCase;
import com.pikachu.purple.application.user.port.in.UserUpdateNicknameUseCase;
import com.pikachu.purple.bootstrap.user.api.UserApi;
import com.pikachu.purple.bootstrap.user.dto.request.RatingRequest;
import com.pikachu.purple.bootstrap.user.dto.request.UpdateNicknameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final RatingSaveUseCase ratingSaveUseCase;
    private final UserUpdateNicknameUseCase userUpdateNicknameUseCase;

    @Override
    public void saveRating(RatingRequest request) {
        ratingSaveUseCase.invoke(
            new RatingSaveUseCase.Command(request.ratingValues())
        );
    }

    @Override
    public void updateNickname(UpdateNicknameRequest request) {
        userUpdateNicknameUseCase.invoke(
            new UserUpdateNicknameUseCase.Command(request.nickname())
        );
    }

}
