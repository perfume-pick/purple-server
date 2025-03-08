package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.UpdateProfileUseCase;
import com.pikachu.purple.application.user.port.out.ImageUrlS3Uploader;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
class UpdateProfileService implements UpdateProfileUseCase {

    private static final String STRING_DEFAULT = "";
    private final UserRepository userRepository;
    private final ImageUrlS3Uploader imageUrlS3Uploader;

    @Override
    public Result update(
        Long userId,
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {
        User user = userRepository.findById(userId);

        isValidToUpdateNickname(
            user,
            nickname
        );

        isValidToUpdateImage(
            user,
            isChanged,
            picture
        );

        return new Result(userRepository.update(user));
    }

    private void isValidToUpdateNickname(
        User user,
        String nickname
    ) {
        if (!user.getNickname().equals(nickname)) {
            userRepository.validateNotExistedNickname(nickname);
            user.updateNickname(nickname);
        }
    }

    private void isValidToUpdateImage(
        User user,
        boolean isChanged,
        MultipartFile picture
    ) {
        if (!isChanged) return;

        deleteExistingImage(user);

        String newImageUrl = changeImage(
            user.getId(),
            picture
        );
        user.updateImageUrl(newImageUrl);
    }

    private void deleteExistingImage(User user) {
        String imageUrl = user.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            imageUrlS3Uploader.delete(imageUrl);
        }
    }

    private String changeImage(Long userId, MultipartFile picture) {
        if (picture == null || picture.isEmpty()) return STRING_DEFAULT;
        return imageUrlS3Uploader.upload(
            userId,
            picture
        );
    }

}
