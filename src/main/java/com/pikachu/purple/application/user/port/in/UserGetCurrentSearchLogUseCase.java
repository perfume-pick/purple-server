package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.entity.UserCurrentSearchLog;
import java.util.List;

public interface UserGetCurrentSearchLogUseCase {

    Result invoke();

    record Result(List<UserCurrentSearchLog> userCurrentSearchLogs){

    }

}
