package com.pikachu.purple.application.history.service.domain.impl;

import com.pikachu.purple.application.history.port.out.PerfumeHistoryRepository;
import com.pikachu.purple.application.history.service.domain.PerfumeHistoryDomainService;
import com.pikachu.purple.domain.history.PerfumeHistory;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeHistoryDomainServiceImpl implements PerfumeHistoryDomainService {

    private final PerfumeHistoryRepository perfumeHistoryRepository;

    @Override
    public void create(
        Long userId,
        Long perfumeId,
        Instant searchAt
    ) {
        PerfumeHistory perfumeHistory = PerfumeHistory.builder()
            .id(userId)
            .perfumeId(perfumeId)
            .searchAt(searchAt)
            .build();

        perfumeHistoryRepository.create(perfumeHistory);
    }

    @Override
    public List<PerfumeHistory> findAllByUserId(Long userId) {
        return perfumeHistoryRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteAllPerfumeHistoryByUserId(Long userId) {
        perfumeHistoryRepository.deleteAllPerfumeHistoryByUserId(userId);
    }


}
