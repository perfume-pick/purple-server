package com.pikachu.purple.application.review.service.mood;

import com.pikachu.purple.application.review.port.in.mood.GetMoodsUseCase;
import com.pikachu.purple.application.review.port.out.MoodRepository;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetMoodsService implements GetMoodsUseCase {

    private final MoodRepository moodRepository;

    @Override
    public Result findAll() {
        List<Mood> moods = moodRepository.findAll();

        return new Result(moods);
    }

}
