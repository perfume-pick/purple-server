package com.pikachu.purple.application.history.service.application.visithistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.visithistory.CreateVisitHistoryUseCase;
import com.pikachu.purple.application.history.service.domain.VisitHistoryDomainService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateVisitHistoryApplicationService implements CreateVisitHistoryUseCase {

    private final VisitHistoryDomainService visitHistoryDomainService;

    @Transactional
    @Override
    public void invoke(
        Long perfumeId,
        Instant searchAt
    ) {
        Long userId = getCurrentUserAuthentication().userId();

        visitHistoryDomainService.create(
            userId,
            perfumeId,
            searchAt
        );
    }

}
