package com.pikachu.purple.application.user.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface UserUpdateProfileUseCase {

    void invoke(Command command);

    record Command(
        String nickname,
        boolean isChanged,
        MultipartFile picture
    ) {

    }

}
