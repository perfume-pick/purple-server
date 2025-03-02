package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewRepository {

    Review create(Long userId, Long perfumeId, Review review);

    void createAll(List<Review> reviews);

    void createReviewMoods(Long reviewId, List<String> moodNames);

    Review find(Long reviewId);

    Review find(Long userId, Long perfumeId);

    List<Review> findAll(Long userId);

    List<Review> findAll(ReviewType reviewType);

    List<Review> findAllOrderByLikeCountDesc(Long userId);

    List<Review> findAllOrderByLikeCountDesc(Perfume perfume);

    List<Review> findAllOrderByCreatedAtDesc(Long userId);

    List<Review> findAllOrderByCreatedAtDesc(Perfume perfume);

    List<Review> findAllOrderByScoreDesc(Long userId);

    List<Review> findAllOrderByScoreDesc(Perfume perfume);

    List<Review> findAllOrderByScoreAsc(Long userId);

    List<Review> findAllOrderByScoreAsc(Perfume perfume);

    void update(Long reviewId, String content, ReviewType reviewType);

    void updateReviewMood(Long reviewId, List<String> moodNames);

    void delete(Long id);

    void deleteReviewMoods(Long reviewId);

    void increaseLikeCount(Long reviewId);

    void decreaseLikeCount(Long reviewId);

    int count();

    int count(Long userId);

}
