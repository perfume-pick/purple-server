package com.pikachu.purple.application.mood.port.out;

import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public interface MoodRepository {

    List<Mood> findAll();

}
