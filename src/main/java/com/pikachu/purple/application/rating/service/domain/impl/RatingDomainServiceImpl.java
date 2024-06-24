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

    private final RatingRepository ratingRepository;

    @Override
    public void create(
        List<Long> ratingIds,
        Long userId,
        List<RatingValue> ratingValues
    ) {
        List<Rating> ratings = IntStream.range(0, ratingIds.size())
            .mapToObj(i -> Rating.builder()
                .ratingId(ratingIds.get(i))
                .userId(userId)
                .perfumeId(ratingValues.get(i).perfumeId())
                .score(ratingValues.get(i).score())
                .build())
            .collect(Collectors.toList());

        ratingRepository.create(ratings);
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        return ratingRepository.getAllByUserId(userId);
    }

}
