package com.pikachu.purple.application.perfume.service.brand;

import com.pikachu.purple.application.perfume.port.in.brand.GetBrandsUseCase;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumesUseCase;
import com.pikachu.purple.application.perfume.port.out.BrandRepository;
import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetBrandsService implements GetBrandsUseCase {

    private final GetPerfumesUseCase getPerfumesUseCase;

    private final BrandRepository brandRepository;

    @Override
    public Result findAll() {
        List<Brand> brands = brandRepository.findAll();

        return new Result(brands);
    }

    @Override
    public Result findAllWithPerfumes(List<String> brandNames) {
        List<Brand> brands = brandRepository.findAll(brandNames);

        for (Brand brand : brands) {
            brand.setPerfumes(getPerfumesUseCase.findAll(brand).perfumes());
        }
        
        return new Result(brands);
    }

}
