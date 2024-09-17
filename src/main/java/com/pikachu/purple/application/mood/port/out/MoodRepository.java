package com.pikachu.purple.application.mood.port.out;

import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import java.util.Set;

public interface MoodRepository {

    List<Mood> findAll();

    /*
    NamesIn -> List<Mood> 반환
     */
    Set<Mood> findAllByNames(List<String> moodNames);

}
