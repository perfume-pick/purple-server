package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.UserSearchHistoryRedisHash;
import java.util.List;

public interface UserSearchHistoryRedisRepository {

    List<UserSearchHistoryRedisHash> findSearchHistoryByUserId(Long userId);

    void saveSearchHistory(
        Long userId,
        UserSearchHistoryRedisHash userSearchHistoryRedisHash
    );

    void deleteAllSearchHistory(Long userId);

}
