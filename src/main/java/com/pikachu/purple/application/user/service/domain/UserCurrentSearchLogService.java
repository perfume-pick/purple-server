package com.pikachu.purple.application.user.service.domain;

import com.pikachu.purple.domain.user.entity.UserCurrentSearchLog;
import java.util.List;

public interface UserCurrentSearchLogService {

    List<UserCurrentSearchLog> findCurrentSearchLogByUserId(Long userId);

    void saveCurrentSearchLog(
        Long userId,
        String keyword,
        String searchAt
    );

    void deleteAllCurrentSearchLog(Long userId);

}
