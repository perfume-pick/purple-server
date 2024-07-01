package com.pikachu.purple.bootstrap.user.controller;

import com.pikachu.purple.application.rating.port.in.RatingSaveUseCase;
import com.pikachu.purple.application.user.port.in.UserUpdateProfileUseCase;
import com.pikachu.purple.bootstrap.user.api.UserApi;
import com.pikachu.purple.bootstrap.user.dto.request.RatingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final RatingSaveUseCase ratingSaveUseCase;
    private final UserUpdateProfileUseCase userUpdateProfileUseCase;

    @Override
    public void saveRating(RatingRequest request) {
        ratingSaveUseCase.invoke(
            new RatingSaveUseCase.Command(request.ratingValues())
        );
    }

    @Override
    public void updateProfile(
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        userUpdateProfileUseCase.invoke(
            new UserUpdateProfileUseCase.Command(
                nickname,
                isChanged,
                picture
            )
        );
    }

}
