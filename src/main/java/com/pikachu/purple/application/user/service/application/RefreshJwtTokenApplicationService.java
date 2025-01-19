package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.RefreshTokenNotFoundException;

import com.pikachu.purple.application.user.port.in.RefreshJwtTokenUseCase;
import com.pikachu.purple.application.user.port.out.UserTokenRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RefreshJwtTokenApplicationService implements RefreshJwtTokenUseCase {

    private final UserDomainService userDomainService;
    private final UserTokenService userTokenService;
    private final UserTokenRepository userTokenRepository;

    @Override
    public Result invoke(String jwtToken) {
        String refreshToken = userTokenService.resolveJwtToken(jwtToken).getRefreshToken();

        Long userId = userTokenService.resolveRefreshToken(refreshToken).getUserId();

        userTokenRepository.findRefreshTokenByUserId(userId)
            .orElseThrow(() -> RefreshTokenNotFoundException);

        User user = userDomainService.findById(userId);
        String accessToken = userTokenService.generateAccessToken(user);
        String newRefreshToken = userTokenService.generateRefreshToken(user);

        return new RefreshJwtTokenUseCase.Result(
            userTokenService.generateJwtToken(
                user,
                accessToken,
                newRefreshToken
            )
        );
    }

}
