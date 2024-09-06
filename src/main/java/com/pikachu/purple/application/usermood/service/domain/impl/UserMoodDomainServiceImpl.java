package com.pikachu.purple.application.usermood.service.domain.impl;

import com.pikachu.purple.application.usermood.port.out.UserMoodRepository;
import com.pikachu.purple.application.usermood.service.domain.UserMoodDomainService;
import com.pikachu.purple.domain.user.entity.UserMood;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMoodDomainServiceImpl implements UserMoodDomainService {

    private final UserMoodRepository userMoodRepository;

    @Override
    public void create(
        List<Long> userMoodIds,
        Long userId,
        Long perfumeId,
        List<String> moods
    ) {
        List<UserMood> userMoods = IntStream.range(0, userMoodIds.size())
            .mapToObj(i -> UserMood.builder()
                .userMoodId(userMoodIds.get(i))
                .userId(userId)
                .perfumeId(perfumeId)
                .moodName(moods.get(i))
                .build())
            .toList();

        userMoodRepository.create(userMoods);
    }

}
