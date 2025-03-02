package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StarRatingJpaRepository extends JpaRepository<StarRatingJpaEntity, Long> {

    Optional<StarRatingJpaEntity> findByUserIdAndPerfumeId(Long userId, Long perfumeId);

    List<StarRatingJpaEntity> findAllByPerfumeId(Long perfumeId);

    List<StarRatingJpaEntity> findAllByUserId(Long userId);

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "left join ReviewJpaEntity re on sr = re.starRatingJpaEntity "
        + "where sr.userId = :userId "
        + "order by re.likeCount desc")
    List<StarRatingJpaEntity> findAllByUserIdOrderByLikeCountDesc(Long userId);

    List<StarRatingJpaEntity> findAllByUserIdOrderByScoreDesc(Long userId);

    List<StarRatingJpaEntity> findAllByUserIdOrderByScoreAsc(Long userId);

}
