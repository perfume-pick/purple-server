package com.pikachu.purple.application.history.service.visithistory;

import com.pikachu.purple.application.history.port.in.visithistory.DeleteVisitHistoriesUseCase;
import com.pikachu.purple.application.history.port.out.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteVisitHistoriesApplicationService implements DeleteVisitHistoriesUseCase {

    private final VisitHistoryRepository visitHistoryRepository;

    @Transactional
    @Override
    public void deleteAll(Long userId) {
        visitHistoryRepository.deleteAllVisitHistoryByUserId(userId);
    }

}
