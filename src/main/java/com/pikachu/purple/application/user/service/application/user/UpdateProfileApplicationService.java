package com.pikachu.purple.application.user.service.application.user;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.user.UpdateProfileUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UpdateProfileApplicationService implements UpdateProfileUseCase {

    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public Result invoke(
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        Long userId = getCurrentUserAuthentication().userId();

        return new Result(
            userDomainService.updateProfile(
                userId,
                nickname,
                isChanged,
                picture
            )
        );
    }

}
