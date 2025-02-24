package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.GetUserCountsUseCase;
import com.pikachu.purple.application.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserCountsService implements GetUserCountsUseCase {

    private final UserRepository userRepository;

    @Override
    public Result count() {
        int userCounts = userRepository.count();
        return new Result(userCounts);
    }
}
