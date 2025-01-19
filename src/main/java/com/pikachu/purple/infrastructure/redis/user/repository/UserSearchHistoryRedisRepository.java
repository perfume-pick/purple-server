package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.SearchHistoryRedisHash;
import java.util.List;

public interface UserSearchHistoryRedisRepository {

    List<SearchHistoryRedisHash> findSearchHistoryByUserId(Long userId);

    void saveSearchHistory(SearchHistoryRedisHash searchHistoryRedisHash);

    void deleteAllSearchHistory(Long userId);

    void validateNotExist(Long userId, String keyword);

}
