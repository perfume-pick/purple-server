package com.pikachu.purple.application.mood.port.in;

import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import java.util.Set;

public interface GetMoodsByNamesUseCase {

    Result invoke(Command command);

    record Command(List<String> moodNames) {}

    record Result(Set<Mood> moods) {}

}
