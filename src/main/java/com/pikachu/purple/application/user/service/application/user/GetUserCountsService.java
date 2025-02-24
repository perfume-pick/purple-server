package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.GetUserCountsUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserCountsService implements GetUserCountsUseCase {

    private final UserDomainService userDomainService;

    @Override
    public Result count() {
        int userCounts = userDomainService.count();
        return new Result(userCounts);
    }
}
