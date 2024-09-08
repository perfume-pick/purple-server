package com.pikachu.purple.application.userevaluation.port.out;

import com.pikachu.purple.domain.user.UserEvaluation;
import java.util.List;

public interface UserEvaluationRepository {

    void create(List<UserEvaluation> userEvaluations);

}
