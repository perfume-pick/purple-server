package com.pikachu.purple.application.history.service.visithistory;

import com.pikachu.purple.application.history.port.in.visithistory.DeleteVisitHistoriesUseCase;
import com.pikachu.purple.application.history.port.out.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class DeleteVisitHistoriesService implements DeleteVisitHistoriesUseCase {

    private final VisitHistoryRepository visitHistoryRepository;

    @Override
    public void deleteAll(Long userId) {
        visitHistoryRepository.deleteAll(userId);
    }

}
