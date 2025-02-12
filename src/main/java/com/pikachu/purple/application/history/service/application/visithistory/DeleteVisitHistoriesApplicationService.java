package com.pikachu.purple.application.history.service.application.visithistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.visithistory.DeleteVisitHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.VisitHistoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteVisitHistoriesApplicationService implements DeleteVisitHistoriesUseCase {

    private final VisitHistoryDomainService visitHistoryDomainService;

    @Transactional
    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        visitHistoryDomainService.deleteAllVisitHistoryByUserId(userId);
    }

}
