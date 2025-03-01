package com.pikachu.purple.application.history.service.visithistory;

import com.pikachu.purple.application.history.port.in.visithistory.CreateVisitHistoryUseCase;
import com.pikachu.purple.application.history.port.out.VisitHistoryRepository;
import com.pikachu.purple.domain.history.VisitHistory;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateVisitHistoryService implements CreateVisitHistoryUseCase {

    private final VisitHistoryRepository visitHistoryRepository;

    @Transactional
    @Override
    public void create(
        Long userId,
        Long perfumeId,
        Instant searchAt
    ) {
        visitHistoryRepository.validateNotExist(
            userId,
            perfumeId
        );

        VisitHistory visitHistory = VisitHistory.builder()
            .id(userId)
            .perfumeId(perfumeId)
            .searchAt(searchAt)
            .build();

        visitHistoryRepository.create(visitHistory);
    }

}
