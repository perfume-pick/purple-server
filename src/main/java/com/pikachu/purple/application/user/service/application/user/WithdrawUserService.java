package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.WithdrawUserUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class WithdrawUserService implements WithdrawUserUseCase {

    private final UserTokenService userTokenService;
    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void withdraw(Long userId) {
        userTokenService.deleteAllToken(userId);
        userDomainService.delete(userId);
    }

}
