package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import java.util.List;
import java.util.Set;

public interface ReviewDomainService {

    void createSimple(
        User user,
        Perfume perfume,
        String content,
        ReviewType reviewType,
        StarRating starRating
    );

    void createDetail(
        User user,
        Perfume perfume,
        String content,
        ReviewType reviewType,
        StarRating starRating,
        Set<Mood> moods,
        ReviewEvaluation reviewEvaluation
    );

    List<Review> findAllByUserId(Long userId);

    void updateContent(
        Long reviewId,
        Long userId,
        String content
    );

    Review findWithPerfumeById(Long reviewId);

    void deleteById(Long Id);

}
