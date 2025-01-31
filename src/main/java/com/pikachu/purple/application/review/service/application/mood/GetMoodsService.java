package com.pikachu.purple.application.review.service.application.mood;

import com.pikachu.purple.application.review.port.in.mood.GetMoodsUseCase;
import com.pikachu.purple.application.review.service.domain.MoodDomainService;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetMoodsService implements GetMoodsUseCase {

    private final MoodDomainService moodDomainService;

    @Override
    public Result invoke() {
        List<Mood> moods = moodDomainService.findAll();

        return new Result(moods);
    }

}
