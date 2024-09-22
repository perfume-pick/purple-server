package com.pikachu.purple.application.perfume.common.vo;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record PerfumeAccordMatchVO(
    Perfume perfume,
    int count,
    List<String> matchAccords
) {}
