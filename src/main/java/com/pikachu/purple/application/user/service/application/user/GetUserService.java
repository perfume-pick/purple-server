package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.GetUserUseCase;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserService implements GetUserUseCase {

    private final UserRepository userRepository;

    @Override
    public Result find(Long userId) {
        User user = userRepository.findById(userId);

        return new Result(user);
    }

}
