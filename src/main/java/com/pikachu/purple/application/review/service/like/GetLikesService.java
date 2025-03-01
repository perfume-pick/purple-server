package com.pikachu.purple.application.review.service.like;

import com.pikachu.purple.application.review.port.in.like.GetLikesUseCase;
import com.pikachu.purple.application.review.port.out.LikeRepository;
import com.pikachu.purple.domain.review.Like;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLikesService implements GetLikesUseCase {

    private final LikeRepository likeRepository;

    @Override
    public Result findAll(Long userId, Long perfumeId) {
        List<Like> likes = likeRepository.findAll(
            userId,
            perfumeId
        );

        return new Result(likes);
    }

}
