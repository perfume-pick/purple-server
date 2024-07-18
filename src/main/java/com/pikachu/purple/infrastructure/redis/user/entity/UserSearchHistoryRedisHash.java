package com.pikachu.purple.infrastructure.redis.user.entity;

import com.pikachu.purple.domain.user.entity.UserSearchHistory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_search_History")
public class UserSearchHistoryRedisHash {

    @Id
    private Long id;

    private String searchName;

    private String searchAt;

    @Builder
    public UserSearchHistoryRedisHash(
        Long id,
        String searchName,
        String searchAt
    ) {
        this.id = id;
        this.searchName = searchName;
        this.searchAt = searchAt;
    }

    public static UserSearchHistory toDomain(UserSearchHistoryRedisHash hash){
        return UserSearchHistory.builder()
            .id(hash.id)
            .searchName(hash.searchName)
            .searchAt(hash.searchAt)
            .build();
    }

}
