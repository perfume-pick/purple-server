package com.pikachu.purple.infrastructure.redis.user.repository.impl;

import com.pikachu.purple.infrastructure.redis.user.entity.UserSearchHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserSearchHistoryRedisRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserSearchHistoryRedisRepositoryImpl implements
    UserSearchHistoryRedisRepository {

    private static final int MAX_SIZE = 9;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<UserSearchHistoryRedisHash> findSearchHistoryByUserId(Long userId){
        return (List<UserSearchHistoryRedisHash>) (List<?>) redisTemplate.opsForList().range(
            "searchHistory:" + userId,
            0,
            MAX_SIZE
        );
    }

    @Override
    public void saveSearchHistory(
        Long userId,
        UserSearchHistoryRedisHash userSearchHistoryRedisHash
    ) {
        Long getCount = redisTemplate.opsForList().size("searchHistory:" + userId);
        redisTemplate.opsForList().leftPush(
            "searchHistory:" + userId,
            userSearchHistoryRedisHash
        );
        if(getCount == 10) redisTemplate.opsForList().trim(
            "searchHistory:" + userId,
            0,
            MAX_SIZE
        );

    }

    @Override
    public void deleteAllSearchHistory(Long userId) {
        redisTemplate.delete("searchHistory:" + userId);
    }

}
