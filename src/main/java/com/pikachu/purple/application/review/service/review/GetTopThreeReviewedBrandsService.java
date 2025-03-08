package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.review.port.in.review.GetTopThreeReviewedBrandsUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetTopThreeReviewedBrandsService implements
    GetTopThreeReviewedBrandsUseCase {

    private final GetPerfumeUseCase getPerfumeUseCase;
    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Result invoke(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        for (Review review : reviews) {
            review.setPerfume(
                getPerfumeUseCase
                    .findByPerfumeId(review.getPerfume().getId())
                    .perfume()
            );
        }


        List<Map.Entry<String, Long>> reviewedBrands = reviews.stream()
            .collect(Collectors.groupingBy(
                review -> review.getPerfume().getBrand().getKoreanName(),
                Collectors.counting()
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(3)
            .toList();

        List<ReviewedBrandDTO> reviewedBrandDTOs = new ArrayList<>();
        for (int i = 0; i < reviewedBrands.size(); i++) {
            int order = i + 1;
            Entry<String, Long> reviewedBrand = reviewedBrands.get(i);
            ReviewedBrandDTO reviewedBrandDTO = new ReviewedBrandDTO(
                order,
                reviewedBrand.getKey(),
                reviewedBrand.getValue().intValue()
            );

            reviewedBrandDTOs.add(reviewedBrandDTO);
        }

        return new Result(reviewedBrandDTOs);
    }

}
