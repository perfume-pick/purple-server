package com.pikachu.purple.application.mood.service.application;

import com.pikachu.purple.application.mood.port.in.MoodGetUseCase;
import com.pikachu.purple.application.mood.service.domain.MoodDomainService;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoodGetApplicationService implements MoodGetUseCase {

    private final MoodDomainService moodDomainService;

    @Override
    public Result invoke() {
        List<Mood> moods = moodDomainService.findAll();

        return new Result(moods);
    }

}
