package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserUpdateProfileUseCase {

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
