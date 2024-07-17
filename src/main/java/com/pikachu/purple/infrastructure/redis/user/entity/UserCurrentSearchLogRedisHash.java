package com.pikachu.purple.infrastructure.redis.user.entity;

import com.pikachu.purple.domain.user.entity.UserCurrentSearchLog;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_current_search_log")
public class UserCurrentSearchLogRedisHash {

    @Id
    private Long id;

    private String searchName;

    private String searchAt;

    @Builder
    public UserCurrentSearchLogRedisHash(
        Long id,
        String searchName,
        String searchAt
    ) {
        this.id = id;
        this.searchName = searchName;
        this.searchAt = searchAt;
    }

    public static UserCurrentSearchLog toDomain(UserCurrentSearchLogRedisHash hash){
        return UserCurrentSearchLog.builder()
            .id(hash.id)
            .searchName(hash.searchName)
            .searchAt(hash.searchAt)
            .build();
    }

}
