package com.pikachu.purple.application.userevaluation.port.out;

import java.util.List;

public interface ReviewEvaluationRepository {

    void create(List<UserEvaluation> userEvaluations);

}
