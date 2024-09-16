package com.pikachu.purple.application.userevaluation.port.out;

import java.util.List;

public interface UserEvaluationRepository {

    void create(List<UserEvaluation> userEvaluations);

}
