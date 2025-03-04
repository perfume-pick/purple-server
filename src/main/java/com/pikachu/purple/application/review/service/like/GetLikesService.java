package com.pikachu.purple.application.review.service.like;

import com.pikachu.purple.application.review.port.in.like.GetLikesUseCase;
import com.pikachu.purple.application.review.port.out.LikeRepository;
import com.pikachu.purple.domain.review.Like;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetLikesService implements GetLikesUseCase {

    private final LikeRepository likeRepository;

    @Transactional
    @Override
    public Result findAllByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        List<Like> likes = likeRepository.findAllByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        return new Result(likes);
    }

}
