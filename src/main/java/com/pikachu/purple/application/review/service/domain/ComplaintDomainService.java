package com.pikachu.purple.application.review.service.domain;

public interface ComplaintDomainService {

    void create(
        Long userId,
        Long reviewId
    );

}
