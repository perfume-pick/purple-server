package com.pikachu.purple.infrastructure.redis.user.entity;

import com.pikachu.purple.domain.history.SearchHistory;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_search_history")
public class SearchHistoryRedisHash {

    @Id
    private Long id;

    private String keyword;

    private Instant searchAt;

    public static SearchHistory toDomain(SearchHistoryRedisHash hash){
        return SearchHistory.builder()
            .id(hash.id)
            .keyword(hash.keyword)
            .build();
    }

}
