package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.LikeRepository;
import com.pikachu.purple.application.review.service.domain.LikeDomainService;
import com.pikachu.purple.domain.review.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDomainServiceImpl implements LikeDomainService {

    private final LikeRepository likeRepository;

    @Override
    public Like find(Long userId, Long reviewId) {
        return likeRepository.find(
            userId,
            reviewId
        );
    }

    @Override
    public void create(
        Long userId,
        Long reviewId
    ) {
        likeRepository.create(
            userId,
            reviewId
        );
    }

    @Override
    public void validateNotExist(
        Long userId,
        Long reviewId
    ) {
        likeRepository.validateNotExist(
            userId,
            reviewId
        );
    }

    @Override
    public void delete(
        Long userId,
        Long reviewId
    ) {
        likeRepository.delete(
            userId,
            reviewId
        );
    }

}
