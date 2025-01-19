package com.pikachu.purple.application.user.service.application.user;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.user.GetUserProfileByUserUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserProfileApplicationService implements GetUserProfileByUserUseCase {

    private final UserDomainService userDomainService;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        User user = userDomainService.findById(userId);

        return new Result(
            user.getNickname(),
            user.getImageUrl(),
            user.getEmail()
        );
    }

}
