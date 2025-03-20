package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.enums.Mood;
import java.util.List;

public interface MoodRepository {

    List<Mood> findAllByReviewId(Long reviewId);

}
