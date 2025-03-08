package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.GetUserUseCase;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetUserService implements GetUserUseCase {

    private final UserRepository userRepository;

    @Override
    public Result findByUserId(Long userId) {
        User user = userRepository.findById(userId);

        return new Result(user);
    }

}
