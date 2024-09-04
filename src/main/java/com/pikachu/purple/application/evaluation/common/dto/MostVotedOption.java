package com.pikachu.purple.application.evaluation.common.dto;

import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;

public record MostVotedOption(
    String optionCode,
    String optionName,
    int votes
) {

    public static MostVotedOption from(FragranticaEvaluation fragranticaEvaluation) {
        return new MostVotedOption(
            fragranticaEvaluation.getOption().getCode(),
            fragranticaEvaluation.getOption().getName(),
            fragranticaEvaluation.getVotes()
        );
    }

}
