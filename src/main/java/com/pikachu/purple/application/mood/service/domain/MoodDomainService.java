package com.pikachu.purple.application.mood.service.domain;

import com.pikachu.purple.domain.mood.Mood;
import java.util.List;

public interface MoodDomainService {

    List<Mood> findAll();

}
