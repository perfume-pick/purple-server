package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface MoodRepository {

    List<Mood> findAll();

    List<Mood> findAll(Review review);

}
