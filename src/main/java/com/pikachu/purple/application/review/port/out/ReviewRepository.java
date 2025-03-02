package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewRepository {

    Review create(Long userId, Long perfumeId, Review review);

    void createAll(List<Review> reviews);

    void createReviewMoods(Long reviewId, List<String> moodNames);

    Review find(Long reviewId);

    List<Review> findAllOrderByLikeCountDesc(Long userId, Long perfumeId);

    List<Review> findAllOrderByCreatedAtDesc(Long userId, Long perfumeId);

    List<Review> findAllOrderByScoreDesc(Long userId, Long perfumeId);

    List<Review> findAllOrderByScoreAsc(Long userId, Long perfumeId);

    List<Review> findAll(ReviewType reviewType);

    void update(Long reviewId, String content, ReviewType reviewType);

    void updateReviewMood(Long reviewId, List<String> moodNames);

    void deleteReviewMoods(Long reviewId);

    void delete(Long id);

    Review find(Long userId, Long perfumeId);

    void increaseLikeCount(Long reviewId);

    void decreaseLikeCount(Long reviewId);

    int count();

    int count(Long userId);

    List<Review> findAll(Long userId);

}
