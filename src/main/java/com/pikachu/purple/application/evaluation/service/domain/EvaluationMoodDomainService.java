package com.pikachu.purple.application.evaluation.service.domain;

import java.util.List;

public interface EvaluationMoodDomainService {

    void create(
        Long userId,
        Long perfumeId,
        List<String> moods
    );

}
