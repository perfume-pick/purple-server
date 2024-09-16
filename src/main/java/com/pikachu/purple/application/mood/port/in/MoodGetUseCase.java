package com.pikachu.purple.application.mood.port.in;

import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public interface MoodGetUseCase {

    Result invoke();

    record Result(List<Mood> moods) {

    }

}
