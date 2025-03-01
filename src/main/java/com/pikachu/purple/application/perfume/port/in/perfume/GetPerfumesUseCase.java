package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface GetPerfumesUseCase {

    Result findAllWithPerfumeAccord(List<Long> perfumeIds);

    Result findAllWithPerfumeAccord(String keyword);

    Result findAllOrderByReviewCount();

    record Result(List<Perfume> perfumes) {}

}
