package com.pikachu.purple.application.history.service.application.purfumehistory;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.history.port.in.perfumehistory.CreatePerfumeHistoryUseCase;
import com.pikachu.purple.application.history.service.domain.PerfumeHistoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePerfumeHistoryApplicationService implements CreatePerfumeHistoryUseCase {

    private final PerfumeHistoryDomainService perfumeHistoryDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();
        perfumeHistoryDomainService.create(
            userId,
            command.perfumeId(),
            command.searchAt()
        );
    }

}
