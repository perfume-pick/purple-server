package com.pikachu.purple.application.review.port.in.mood;

import com.pikachu.purple.domain.review.enums.Mood;
import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface GetMoodsUseCase {

    Result findAll(Review review);

    record Result(List<Mood> moods) {}

}
