package com.pikachu.purple.application.usermood.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.usermood.port.in.UserMoodCreateUseCase;
import com.pikachu.purple.application.usermood.service.domain.UserMoodDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMoodCreateApplicationService implements UserMoodCreateUseCase {

    private final UserMoodDomainService userMoodDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        if(command.moods() == null || command.moods().isEmpty()) return;

        List<Long> userMoodIds = IntStream.range(0, command.moods().size())
                .mapToObj(i -> IdGenerator.generate())
                .toList();

        userMoodDomainService.create(
            userMoodIds,
            userId,
            command.perfumeId(),
            command.moods()
        );
    }

}
