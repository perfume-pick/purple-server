package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.application.history.port.out.SearchHistoryRepository;
import com.pikachu.purple.domain.history.SearchHistory;
import com.pikachu.purple.infrastructure.redis.user.entity.SearchHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserSearchHistoryRedisRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchHistoryRedisAdaptor implements SearchHistoryRepository {

    private final UserSearchHistoryRedisRepository userSearchHistoryRedisRepository;

    @Override
    public List<SearchHistory> findAllByUserId(Long userId) {
        List<SearchHistoryRedisHash> searchHistoryRedisHashes = userSearchHistoryRedisRepository.findSearchHistoryByUserId(userId);

        return searchHistoryRedisHashes.stream()
            .map(SearchHistoryRedisHash::toDomain)
            .toList();
    }

    @Override
    public void create(
        SearchHistory searchHistory,
        Instant searchAt
    ) {
        SearchHistoryRedisHash searchHistoryRedisHash = SearchHistoryRedisHash.toHash(
            searchHistory,
            searchAt
        );

        userSearchHistoryRedisRepository.saveSearchHistory(searchHistoryRedisHash);
    }

    @Override
    public void deleteAllSearchHistoryByUserId(Long userId) {
        userSearchHistoryRedisRepository.deleteAllSearchHistory(userId);
    }

    @Override
    public void validateNotExist(Long userId, String keyword) {

    }

}
