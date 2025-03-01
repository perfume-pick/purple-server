package com.pikachu.purple.application.history.service.visithistory;

import com.pikachu.purple.application.history.port.in.visithistory.GetVisitHistoriesUseCase;
import com.pikachu.purple.application.history.port.out.VisitHistoryRepository;
import com.pikachu.purple.domain.history.VisitHistory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetVisitHistoriesService implements GetVisitHistoriesUseCase {

    private final VisitHistoryRepository visitHistoryRepository;

    @Override
    public Result findAll(Long userId) {
        List<VisitHistory> visitHistories = visitHistoryRepository.findAllByUserId(userId);

        return new Result(visitHistories);
    }

}
