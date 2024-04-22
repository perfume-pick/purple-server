package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.UserEmailVerificationRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailVerificationRedisRepository extends CrudRepository<UserEmailVerificationRedisHash, String> {

}
