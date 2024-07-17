package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.UserGetCurrentSearchLogUseCase;
import com.pikachu.purple.application.user.service.domain.UserCurrentSearchLogService;
import com.pikachu.purple.domain.user.entity.UserCurrentSearchLog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetCurrentSearchLogApplicationService implements UserGetCurrentSearchLogUseCase {

    private final UserCurrentSearchLogService userCurrentSearchLogService;

    @Override
    public Result invoke() {
        Long userId = 1L;

        List<UserCurrentSearchLog> userCurrentSearchLogs = userCurrentSearchLogService.findCurrentSearchLogByUserId(userId);

        return new Result(userCurrentSearchLogs);
    }

}
