package com.pikachu.purple.infrastructure.redis.user.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.pikachu.purple.domain.user.entity.UserSearchHistory;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_search_history")
public class UserSearchHistoryRedisHash {

    @Id
    private Long id;

    private String searchName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime searchAt;

    @Builder
    public UserSearchHistoryRedisHash(
        Long id,
        String searchName,
        LocalDateTime searchAt
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
