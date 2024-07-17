package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.UserDeleteAllCurrentSearchLogUseCase;
import com.pikachu.purple.application.user.service.domain.UserCurrentSearchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteAllCurrentCurrentSearchLogApplicationService implements
    UserDeleteAllCurrentSearchLogUseCase {

    private final UserCurrentSearchLogService userCurrentSearchLogService;

    @Override
    public void invoke() {
        Long userId = 1L;
        userCurrentSearchLogService.deleteAllCurrentSearchLog(userId);
    }

}
