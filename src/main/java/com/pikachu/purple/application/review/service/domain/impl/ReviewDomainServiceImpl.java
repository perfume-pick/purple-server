package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDomainServiceImpl implements ReviewDomainService {

    private final ReviewRepository reviewRepository;

    @Override
    public void createSimple(
        User user,
        Perfume perfume,
        String content,
        ReviewType reviewType,
        StarRating starRating
    ) {
        Long reviewId = IdGenerator.generate();

        Review review = Review.builder()
            .id(reviewId)
            .user(user)
            .perfume(perfume)
            .content(content)
            .type(reviewType)
            .starRating(starRating)
            .build();

        reviewRepository.create(review);
    }

    @Override
    public void createDetail(
        User user,
        Perfume perfume,
        String content,
        ReviewType reviewType,
        StarRating starRating,
        Set<Mood> moods,
        ReviewEvaluation reviewEvaluation
    ) {
        Long reviewId = IdGenerator.generate();

        Review review = Review.builder()
            .id(reviewId)
            .user(user)
            .perfume(perfume)
            .type(reviewType)
            .starRating(starRating)
            .moods(moods)
            .evaluation(reviewEvaluation)
            .build();

        reviewRepository.create(review);
    }

    @Override
    public List<Review> findAllByUserId(Long userId) {
        return reviewRepository.findAllByUserId(userId);
    }

    @Override
    public void updateContent(
        Long reviewId,
        Long userId,
        String content
    ) {
        Review review = reviewRepository.getByIdAndUserId(
            reviewId,
            userId
        );

        review.updateContent(content);

        reviewRepository.save(review);
    }

    @Override
    public Review findWithPerfumeById(Long reviewId) {
        return reviewRepository.findWithPerfumeById(reviewId);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

}
