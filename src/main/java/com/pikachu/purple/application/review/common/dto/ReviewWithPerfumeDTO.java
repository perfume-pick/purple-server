package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.application.util.DateUtil;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public record ReviewWithPerfumeDTO(
    String reviewId,
    String brandName,
    String perfumeId,
    String perfumeName,
    String perfumeImageUrl,
    ReviewType reviewType,
    int score,
    String content,
    List<ReviewEvaluationFieldDTO> perfumeEvaluations,
    List<String> moodNames,
    Boolean isComplained,
    Boolean isLiked,
    Integer likeCount,
    String date
) {
    public static ReviewWithPerfumeDTO of(
        StarRating starRating,
        List<ReviewEvaluationFieldDTO> perfumeEvaluations,
        List<String> moodNames
    ) {
        return new ReviewWithPerfumeDTO(
            IdUtil.toString(starRating.getReview().getId()),
            starRating.getReview().getPerfume().getBrand().getKoreanName(),
            IdUtil.toString(starRating.getPerfume().getId()),
            starRating.getReview().getPerfume().getKoreanName(),
            starRating.getReview().getPerfume().getImageUrl(),
            starRating.getReview().getType(),
            starRating.getScore(),
            starRating.getReview().getContent(),
            perfumeEvaluations,
            moodNames,
            starRating.getReview().isComplained(),
            starRating.getReview().isLiked(),
            starRating.getReview().getLikeCount(),
            DateUtil.toString(starRating.getReview().getUpdatedAt())
        );
    }

    public static ReviewWithPerfumeDTO from(StarRating starRating) {
        return new ReviewWithPerfumeDTO(
            null,
            starRating.getPerfume().getBrand().getKoreanName(),
            IdUtil.toString(starRating.getPerfume().getId()),
            starRating.getPerfume().getKoreanName(),
            starRating.getPerfume().getImageUrl(),
            null,
            starRating.getScore(),
            null,
            null,
            null,
            null,
            null,
            null,
            DateUtil.toString(starRating.getUpdatedAt())
        );
    }
}
