package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.GetUserByIdUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdApplicationService implements GetUserByIdUseCase {

    private final UserDomainService userDomainService;

    @Override
    public Result invoke(Command command) {
        User user = userDomainService.findById(command.userId());

        return new Result(user);
    }
}
