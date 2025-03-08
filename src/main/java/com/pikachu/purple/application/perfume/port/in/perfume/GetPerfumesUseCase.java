package com.pikachu.purple.application.perfume.port.in.perfume;

import com.pikachu.purple.domain.perfume.Brand;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public interface GetPerfumesUseCase {

    Result findAll(Brand brand);

    Result findAllWithPerfumeAccord(List<Long> perfumeIds);

    Result findAllWithPerfumeAccord(String keyword);

    Result findAllWithPerfumeAccordOrderByReviewCount();

    record Result(List<Perfume> perfumes) {}

}
