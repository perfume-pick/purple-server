package com.pikachu.purple.application.mood.service.domain;

import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import java.util.Set;

public interface MoodDomainService {

    List<Mood> findAll();

    Set<Mood> findAllByNames(List<String> moodNames);

}
