package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.Mood;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewRepository {

    Review create(Long userId, Long perfumeId, Review review);

    void createAll(List<Review> reviews);

    void createReviewMoods(Long reviewId, List<Mood> moods);

    Review findByReviewId(Long reviewId);

    Review findUserIdAndPerfumeId(Long userId, Long perfumeId);

    List<Review> findAllByUserId(Long userId);

    List<Review> findAll(ReviewType reviewType);

    List<Review> findAllByUserIdOrderByLikeCountDesc(Long userId);

    List<Review> findAllByPerfumeIdOrderByLikeCountDesc(Long perfumeId);

    List<Review> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    List<Review> findAllByPerfumeIdOrderByCreatedAtDesc(Long perfumeId);

    List<Review> findAllByUserIdOrderByScoreDesc(Long userId);

    List<Review> findAllByPerfumeIdOrderByScoreDesc(Long perfumeId);

    List<Review> findAllByUserIdOrderByScoreAsc(Long userId);

    List<Review> findAllByPerfumeIdOrderByScoreAsc(Long perfumeId);

    void update(Long reviewId, String content, ReviewType reviewType);

    void updateReviewMood(Long reviewId, List<Mood> moods);

    void delete(Long id);

    void deleteReviewMoods(Long reviewId);

    void increaseLikeCount(Long reviewId);

    void decreaseLikeCount(Long reviewId);

    int count();

    int count(Long userId);

}
