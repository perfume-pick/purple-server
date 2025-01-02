package com.pikachu.purple.application.user.service.application.user;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.user.DeleteUserUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserApplicationService implements DeleteUserUseCase {

    private final UserTokenService userTokenService;
    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        userTokenService.deleteAllToken(userId);
        userDomainService.delete(userId);
    }

}
