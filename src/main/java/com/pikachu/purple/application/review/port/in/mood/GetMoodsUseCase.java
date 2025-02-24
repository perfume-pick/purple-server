package com.pikachu.purple.application.review.port.in.mood;

import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public interface GetMoodsUseCase {

    Result findAll();

    record Result(List<Mood> moods) {}

}
