package com.pikachu.purple.application.mood.service.application;

import com.pikachu.purple.application.mood.port.in.GetMoodsByNamesUseCase;
import com.pikachu.purple.application.mood.service.domain.MoodDomainService;
import com.pikachu.purple.domain.review.Mood;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMoodsByNamesApplicationService implements GetMoodsByNamesUseCase {

    private final MoodDomainService moodDomainService;

    @Override
    public Result invoke(Command command) {
        Set<Mood> moods = moodDomainService.findAllByNames(command.moodNames());

        return new Result(moods);
    }
}
