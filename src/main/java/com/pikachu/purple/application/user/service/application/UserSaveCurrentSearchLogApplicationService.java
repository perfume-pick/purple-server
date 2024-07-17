package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.UserSaveCurrentSearchLogUseCase;
import com.pikachu.purple.application.user.service.domain.UserCurrentSearchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSaveCurrentSearchLogApplicationService implements UserSaveCurrentSearchLogUseCase {

    private final UserCurrentSearchLogService userCurrentSearchLogService;

    @Override
    public void invoke(
        String keyword,
        String searchAt
    ) {
        Long userId = 1L;
        userCurrentSearchLogService.saveCurrentSearchLog(
            userId,
            keyword,
            searchAt
        );
    }
}
