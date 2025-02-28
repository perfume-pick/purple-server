package com.pikachu.purple.application.perfume.port.in.brand;

import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;

public interface GetBrandsUseCase {

    Result findAll();

    Result findAllWithPerfumes(List<String> brandNames);

    record Result(List<Brand> brands) {}

}
