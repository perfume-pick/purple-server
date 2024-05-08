package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.UserJwtTokenRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJwtTokenRedisRepository extends CrudRepository<UserJwtTokenRedisHash,Long> {
}
