package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.SearchHistoryRepository;
import com.pikachu.purple.application.user.service.domain.SearchHistoryDomainService;
import com.pikachu.purple.domain.history.SearchHistory;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchHistoryDomainServiceImpl implements SearchHistoryDomainService {

    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public List<SearchHistory> findAllByUserId(Long userId) {
       return searchHistoryRepository.findAllByUserId(userId);
    }

    @Override
    public void createSearchHistory(
        Long userId,
        String keyword,
        Instant searchAt
    ) {
        searchHistoryRepository.createSearchHistory(
            userId,
            keyword,
            searchAt
        );
    }

    @Override
    public void deleteAllSearchHistoryByUserId(Long userId) {
        searchHistoryRepository.deleteAllSearchHistoryByUserId(userId);
    }

}