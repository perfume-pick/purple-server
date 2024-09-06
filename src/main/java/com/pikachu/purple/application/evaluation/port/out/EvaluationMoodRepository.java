package com.pikachu.purple.application.evaluation.port.out;

import com.pikachu.purple.domain.evaluation.EvaluationMood;
import java.util.List;

public interface EvaluationMoodRepository {

    void create(List<EvaluationMood> evaluationMoods);

}
