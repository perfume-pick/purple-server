package com.pikachu.purple.application.review.port.out;

public interface ComplaintRepository {

    void create(
        Long userId,
        Long reviewId
    );

}
