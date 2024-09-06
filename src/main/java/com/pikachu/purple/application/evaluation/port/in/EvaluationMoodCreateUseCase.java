package com.pikachu.purple.application.evaluation.port.in;

import java.util.List;

public interface EvaluationMoodCreateUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        List<String> moods
    ){

    }

}
