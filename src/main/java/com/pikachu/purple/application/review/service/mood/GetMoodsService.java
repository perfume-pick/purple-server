package com.pikachu.purple.application.review.service.mood;

import com.pikachu.purple.application.review.port.in.mood.GetMoodsUseCase;
import com.pikachu.purple.application.review.port.out.MoodRepository;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetMoodsService implements GetMoodsUseCase {

    private final MoodRepository moodRepository;

    @Transactional
    @Override
    public Result findAll() {
        List<Mood> moods = moodRepository.findAll();

        return new Result(moods);
    }

    @Transactional
    @Override
    public Result findAll(Review review) {
        List<Mood> moods = moodRepository.findAllByReviewId(review.getId());

        return new Result(moods);
    }

}
