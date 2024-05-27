package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.RefreshJwtTokenUseCase;
import com.pikachu.purple.application.user.port.out.UserTokenRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshJwtTokenApplicationService implements RefreshJwtTokenUseCase {

    private final UserDomainService userDomainService;
    private final UserTokenService userTokenService;
    private final UserTokenRepository userTokenRepository;

    @Override
    public Result invoke(Command command) {
        String jwtToken = command.jwtToken();
        String refreshToken = userTokenService.resolveJwtToken(jwtToken).getRefreshToken();

        Long userId = userTokenService.resolveRefreshToken(refreshToken).getUserId();

        userTokenRepository.findRefreshTokenByUserId(userId)
            .orElseThrow(() -> new BusinessException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        User user = userDomainService.getById(userId);
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
