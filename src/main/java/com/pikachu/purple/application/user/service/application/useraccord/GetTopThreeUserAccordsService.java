package com.pikachu.purple.application.user.service.application.useraccord;

import com.pikachu.purple.application.user.port.in.useraccord.GetTopThreeUserAccordsUseCase;
import com.pikachu.purple.application.user.port.out.UserAccordRepository;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetTopThreeUserAccordsService implements GetTopThreeUserAccordsUseCase {

    private final UserAccordRepository userAccordRepository;
    private static final int MAX_SIZE = 3;

    @Transactional
    @Override
    public Result findByUserId(Long userId) {
        List<UserAccord> userAccords = userAccordRepository.findAllOrderByScoreDesc(
            userId,
            MAX_SIZE
        );

        return new Result(userAccords);
    }

}
