package com.pikachu.purple.application.history.service.domain.impl;

import com.pikachu.purple.application.history.port.out.VisitHistoryRepository;
import com.pikachu.purple.application.history.service.domain.VisitHistoryDomainService;
import com.pikachu.purple.domain.history.VisitHistory;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitHistoryDomainServiceImpl implements VisitHistoryDomainService {

    private final VisitHistoryRepository visitHistoryRepository;

    @Override
    public void create(
        Long userId,
        Long perfumeId,
        Instant searchAt
    ) {
        VisitHistory visitHistory = VisitHistory.builder()
            .id(userId)
            .perfumeId(perfumeId)
            .searchAt(searchAt)
            .build();

        visitHistoryRepository.create(visitHistory);
    }

    @Override
    public List<VisitHistory> findAllByUserId(Long userId) {
        return visitHistoryRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteAllVisitHistoryByUserId(Long userId) {
        visitHistoryRepository.deleteAllVisitHistoryByUserId(userId);
    }

    @Override
    public void validateNotExist(
        Long userId,
        Long perfumeId
    ) {
        visitHistoryRepository.validateNotExist(
            userId,
            perfumeId
        );
    }

}
