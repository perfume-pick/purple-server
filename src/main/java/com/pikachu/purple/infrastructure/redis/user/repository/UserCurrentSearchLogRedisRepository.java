package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.UserCurrentSearchLogRedisHash;
import java.util.List;

public interface UserCurrentSearchLogRedisRepository {

    List<UserCurrentSearchLogRedisHash> findCurrentSearchLogByUserId(Long userId);

    void saveCurrentSearchLog(
        Long userId,
        UserCurrentSearchLogRedisHash userCurrentSearchLogRedisHash
    );

    void deleteAllCurrentSearchLog(Long userId);

}
