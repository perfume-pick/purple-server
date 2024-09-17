package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.UpdateProfileUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProfileApplicationService implements UpdateProfileUseCase {

    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public Result invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        return new Result(
            userDomainService.updateProfile(
                userId,
                command.nickname(),
                command.isChanged(),
                command.picture()
            )
        );
    }

}
