package com.pikachu.purple.application.user.service.application.user;

import com.pikachu.purple.application.user.port.in.user.GetUserCountsUseCase;
import com.pikachu.purple.application.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetUserCountsService implements GetUserCountsUseCase {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public Result count() {
        int userCounts = userRepository.count();
        return new Result(userCounts);
    }

}
