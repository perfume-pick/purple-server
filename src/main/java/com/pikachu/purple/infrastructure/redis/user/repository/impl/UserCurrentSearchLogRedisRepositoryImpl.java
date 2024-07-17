package com.pikachu.purple.infrastructure.redis.user.repository.impl;

import com.pikachu.purple.infrastructure.redis.user.entity.UserCurrentSearchLogRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserCurrentSearchLogRedisRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCurrentSearchLogRedisRepositoryImpl implements
    UserCurrentSearchLogRedisRepository {

    private static final int MAX_SIZE = 9;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<UserCurrentSearchLogRedisHash> findCurrentSearchLogByUserId(Long userId){
        return (List<UserCurrentSearchLogRedisHash>) (List<?>) redisTemplate.opsForList().range("searchLog:" + userId, 0, MAX_SIZE);
    }

    @Override
    public void saveCurrentSearchLog(
        Long userId,
        UserCurrentSearchLogRedisHash userCurrentSearchLogRedisHash
    ) {
        Long getCount = redisTemplate.opsForList().size("searchLog:" + userId);
        redisTemplate.opsForList().leftPush("searchLog:" + userId, userCurrentSearchLogRedisHash);
        if(getCount == 10) {
            redisTemplate.opsForList().trim("searchLog:" + userId, 0, MAX_SIZE);
        }
    }

    @Override
    public void deleteAllCurrentSearchLog(Long userId) {
        redisTemplate.delete("searchLog:" + userId);
    }

}
