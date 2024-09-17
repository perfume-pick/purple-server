package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeDomainServiceImpl implements PerfumeDomainService {

    private final PerfumeRepository perfumeRepository;

    @Override
    public List<Perfume> findAllByUserAccords(List<UserAccord> userAccords) {
        return perfumeRepository.findAllByUserAccords(userAccords);
    }

    @Override
    public List<Perfume> findAllByKeyword(String keyword) {
        return perfumeRepository.findAllByKeyword("%" + keyword + "%");
    }

    @Override
    public Perfume findById(Long perfumeId) {
        return perfumeRepository.findById(perfumeId);
    }

    @Override
    public List<Perfume> findAllByBrandNames(List<String> brandNames) {
        return perfumeRepository.findAllByBrandNames(brandNames);
    }

    @Override
    public List<Perfume> findAllByIds(List<Long> perfumeIds) {
        return perfumeRepository.findAllByIds(perfumeIds);
    }

}
