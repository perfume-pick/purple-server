package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.GetUserByIdUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserByIdApplicationService implements GetUserByIdUseCase {

    private final UserDomainService userDomainService;

    @Override
    public Result invoke(Long userId) {
        User user = userDomainService.findById(userId);

        return new Result(user);
    }

}
