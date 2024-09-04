package com.pikachu.purple.application.evaluation.port.out;

import com.pikachu.purple.domain.evaluation.EvaluationCode;
import java.util.List;

public interface EvaluationCodeRepository {

    List<EvaluationCode> findAll();

}
