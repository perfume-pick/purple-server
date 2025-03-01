package com.pikachu.purple.application.review.port.in.like;

import com.pikachu.purple.domain.review.Like;
import java.util.List;

public interface GetLikesUseCase {

    Result findAll(Long userId, Long perfumeId);

    record Result(List<Like> likes) {}

}
