package com.pikachu.purple.application.history.service.application.purfumehistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.perfumehistory.DeletePerfumeHistoriesUseCase;
import com.pikachu.purple.application.history.service.domain.PerfumeHistoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePerfumeHistoriesApplicationService implements DeletePerfumeHistoriesUseCase {

    private final PerfumeHistoryDomainService perfumeHistoryDomainService;

    @Transactional
    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        perfumeHistoryDomainService.deleteAllPerfumeHistoryByUserId(userId);
    }

}
