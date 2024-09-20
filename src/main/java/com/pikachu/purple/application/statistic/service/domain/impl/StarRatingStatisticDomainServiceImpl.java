package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarRatingStatisticDomainServiceImpl implements StarRatingStatisticDomainService {

    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    public List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId) {
        return starRatingStatisticRepository.findAllByPerfumeId(perfumeId);
    }

}
