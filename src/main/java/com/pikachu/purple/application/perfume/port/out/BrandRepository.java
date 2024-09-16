package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;

public interface BrandRepository {

    List<Brand> findAll();

    List<Brand> findAllByBrandNames(List<String> brands);

}
