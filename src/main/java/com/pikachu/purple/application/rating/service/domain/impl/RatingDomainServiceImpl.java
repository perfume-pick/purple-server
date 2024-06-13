package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.RatingRepository;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.bootstrap.user.vo.RatingValue;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingDomainServiceImpl implements RatingDomainService {

    private static final int ZERO = 0;
    private final RatingRepository ratingRepository;

    @Override
    public void create(
        List<Long> ratingIdList,
        Long userId,
        List<RatingValue> ratingValueList
    ) {
        List<Rating> ratingList = IntStream.range(ZERO, ratingIdList.size())
            .mapToObj(i -> Rating.builder()
                .ratingId(ratingIdList.get(i))
                .userId(userId)
                .perfumeId(ratingValueList.get(i).perfumeId())
                .score(ratingValueList.get(i).score())
                .build())
            .collect(Collectors.toList());

        ratingRepository.create(ratingList);
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        return ratingRepository.getAllByUserId(userId);
    }

}
