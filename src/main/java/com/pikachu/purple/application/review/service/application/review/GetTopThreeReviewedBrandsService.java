package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.common.dto.ReviewedBrandDTO;
import com.pikachu.purple.application.review.port.in.review.GetTopThreeReviewedBrandsUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
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

    private final ReviewDomainService reviewDomainService;

    @Override
    @Transactional
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        List<Review> reviews = reviewDomainService.findAllWithPerfume(userId);

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
            ReviewedBrandDTO reviewedBrandDTO = ReviewedBrandDTO.builder()
                .order(order)
                .brandName(reviewedBrand.getKey())
                .reviewCounts(reviewedBrand.getValue().intValue())
                .build();

            reviewedBrandDTOs.add(reviewedBrandDTO);
        }

        return new Result(reviewedBrandDTOs);
    }

}
