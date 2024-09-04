package com.pikachu.purple.application.evaluation.port.out;

import com.pikachu.purple.domain.evaluation.EvaluationFieldOption;
import java.util.List;

public interface EvaluationFieldOptionRepository {

    List<EvaluationFieldOption> findAll();

}
