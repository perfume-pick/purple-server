package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.UpdateProfileUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class UpdateProfileService implements UpdateProfileUseCase {

    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public Result update(
        Long userId,
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
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
