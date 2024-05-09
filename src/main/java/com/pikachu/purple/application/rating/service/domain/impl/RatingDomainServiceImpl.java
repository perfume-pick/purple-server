package com.pikachu.purple.application.rating.service.domain.impl;

import com.pikachu.purple.application.rating.port.out.RatingRepository;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.bootstrap.user.vo.RatingValue;
import com.pikachu.purple.domain.rating.Rating;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingDomainServiceImpl implements RatingDomainService {

    private final RatingRepository ratingRepository;

    @Override
    public void create(
        Long ratingId,
        Long userId,
        List<RatingValue> ratingValueList
    ) {
        List<Rating> ratingList = new ArrayList<>();
        for(RatingValue value : ratingValueList){
            Rating rating = Rating.builder()
                .ratingId(ratingId)
                .userId(userId)
                .perfumeId(value.getPerfumeId())
                .score(value.getScore())
                .build();

            ratingList.add(rating);
        }

        ratingRepository.create(ratingList);
    }

}
