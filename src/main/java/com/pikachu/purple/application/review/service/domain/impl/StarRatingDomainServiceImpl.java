package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.StarRatingRepository;
import com.pikachu.purple.application.review.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StarRatingDomainServiceImpl implements StarRatingDomainService {

    private final StarRatingRepository starRatingRepository;

    @Override
    public void createOnboarding(
        Long userId,
        List<StarRatingVO> starRatingVOs
    ) {
        List<StarRating> starRatings = new ArrayList<>();

        for(StarRatingVO starRatingVO : starRatingVOs) {
            Long starRatingId = IdUtil.generateId();

            Perfume perfume = Perfume.builder()
                .id(starRatingVO.perfumeId())
                .build();

            StarRating starRating = StarRating.builder()
                .id(starRatingId)
                .perfume(perfume)
                .score(starRatingVO.score())
                .build();

            starRatings.add(starRating);
        }

        starRatingRepository.createOnboarding(
            userId,
            starRatings
        );
    }

    @Override
    public StarRating create(
        Long userId,
        Long perfumeId,
        int score
    ) {
        Long starRatingId = IdUtil.generateId();
        return starRatingRepository.create(starRatingId, userId, perfumeId, score);
    }

    @Override
    public List<StarRating> findAllWithPerfumeAndPerfumeAccordByUserId(Long userId) {
        return starRatingRepository.findAllWithPerfumeAndPerfumeAccordByUserId(userId);
    }

    @Override
    public StarRating updateScore(
        Long userId,
        Long perfumeId,
        int score
    ) {
        return starRatingRepository.updateScore(
            userId,
            perfumeId,
            score
        );
    }

    @Override
    public StarRating deleteById(Long starRatingId) {
        return starRatingRepository.deleteById(starRatingId);
    }

    @Override
    public StarRating findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        return starRatingRepository.findByUserIdAndPerfumeId(
            userId,
            perfumeId
        );
    }

    @Override
    public List<StarRating> findAll() {
        return starRatingRepository.findAll();
    }

    @Override
    public List<StarRating> findAll(Long perfumeId) {
        return starRatingRepository.findAll(perfumeId);
    }

    @Override
    public List<StarRating> findAllByUpdatedDate(String updatedDate) {
        return starRatingRepository.findAllByUpdatedDate(updatedDate);
    }

    @Override
    public List<StarRating> findAllOrderByLikeCountDesc(Long userId) {
        return starRatingRepository.findAllOrderByLikeCountDesc(userId);
    }

    @Override
    public List<StarRating> findAllByUserId(Long userId) {
        return starRatingRepository.findAllByUserId(userId);
    }

    @Override
    public List<StarRating> findAllOrderByScoreDesc(Long userId) {
        return starRatingRepository.findAllOrderByScoreDesc(userId);
    }

    @Override
    public List<StarRating> findAllOrderByScoreAsc(Long userId) {
        return starRatingRepository.findALlOrderByScoreAsc(userId);
    }

}
