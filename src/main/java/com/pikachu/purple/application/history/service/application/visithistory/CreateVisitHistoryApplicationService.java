package com.pikachu.purple.application.history.service.application.visithistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.visithistory.CreateVisitHistoryUseCase;
import com.pikachu.purple.application.history.service.domain.VisitHistoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateVisitHistoryApplicationService implements CreateVisitHistoryUseCase {

    private final VisitHistoryDomainService visitHistoryDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();
        visitHistoryDomainService.create(
            userId,
            command.perfumeId(),
            command.searchAt()
        );
    }

}
