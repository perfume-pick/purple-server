package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.GetUserCountsUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserCountsApplicationService implements GetUserCountsUseCase {

    private final UserDomainService userDomainService;

    @Override
    public Result invoke() {
        int userCounts = userDomainService.count();
        return new Result(userCounts);
    }
}
