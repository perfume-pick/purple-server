package com.pikachu.purple.application.user.port.in.user;

import com.pikachu.purple.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateProfileUseCase {

    Result invoke(Command command);

    record Command(
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {

    }

    record Result(User user) {

    }

}
