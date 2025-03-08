package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.WithdrawUserUseCase;
import com.pikachu.purple.application.user.port.out.ImageUrlS3Uploader;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class WithdrawUserService implements WithdrawUserUseCase {

    private static final String NICKNAME_ANONYMIZATION = "익명의 사용자";
    private static final String STRING_DEFAULT = "";

    private final UserTokenService userTokenService;
    private final UserRepository userRepository;
    private final ImageUrlS3Uploader imageUrlS3Uploader;

    @Override
    public void withdraw(Long userId) {
        userTokenService.deleteAllToken(userId);
        User user = userRepository.findById(userId);

        deleteExistingImage(user);

        user.updateEmail(STRING_DEFAULT);
        user.updateNickname(NICKNAME_ANONYMIZATION);
        user.updateImageUrl(STRING_DEFAULT);

        userRepository.update(user);
    }

    private void deleteExistingImage(User user) {
        String imageUrl = user.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            imageUrlS3Uploader.delete(imageUrl);
        }
    }

}
