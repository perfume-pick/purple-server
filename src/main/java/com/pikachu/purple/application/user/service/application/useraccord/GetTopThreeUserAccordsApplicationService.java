package com.pikachu.purple.application.user.service.application.useraccord;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.useraccord.GetTopThreeUserAccordsUseCase;
import com.pikachu.purple.application.user.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetTopThreeUserAccordsApplicationService implements GetTopThreeUserAccordsUseCase {

    private final UserAccordDomainService userAccordDomainService;
    private static final int MAX_SIZE = 3;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        List<UserAccord> userAccords = userAccordDomainService.findAllOrderByScoreDesc(
            userId,
            MAX_SIZE
        );

        return new Result(userAccords);
    }

}
