package com.pikachu.purple.bootstrap.user.controller;

import com.pikachu.purple.application.user.port.in.UserDeleteAllSearchHistoryUseCase;
import com.pikachu.purple.application.user.port.in.UserGetSearchHistoryUseCase;
import com.pikachu.purple.application.user.port.in.UserUpdateProfileUseCase;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.bootstrap.user.api.UserApi;
import com.pikachu.purple.bootstrap.user.dto.response.SearchPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserUpdateProfileUseCase userUpdateProfileUseCase;
    private final UserGetSearchHistoryUseCase userGetSearchHistoryUseCase;
    private final UserDeleteAllSearchHistoryUseCase userDeleteAllSearchLogUseCase;

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

    @Override
    public SuccessResponse<SearchPageResponse> getSearchHistories() {
        UserGetSearchHistoryUseCase.Result getUserSearchHistories = userGetSearchHistoryUseCase.invoke();

        return SuccessResponse.of(new SearchPageResponse(getUserSearchHistories.userSearchHistories()));
    }

    @Override
    public void deleteSearchHistories() {
        userDeleteAllSearchLogUseCase.invoke();
    }

}
