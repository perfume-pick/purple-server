package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, Long> {

    Optional<ReviewJpaEntity> findByUserIdAndPerfumeId(Long userId, Long perfumeId);

    List<ReviewJpaEntity> findAllByUserId(Long userId);

    List<ReviewJpaEntity> findAllByReviewType(ReviewType reviewType);

    List<ReviewJpaEntity> findAllByPerfumeIdOrderByLikeCountDesc(Long perfumeId);

    List<ReviewJpaEntity> findAllByPerfumeIdOrderByCreatedAtDesc(Long perfumeId);

    @Query("select r "
        + "from ReviewJpaEntity r"
        + " left join StarRatingJpaEntity sr on sr.id = r.starRatingId "
        + "where r.perfumeId = :perfumeId "
        + "order by sr.score desc")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByScoreDesc(Long perfumeId);

    @Query("select r "
        + "from ReviewJpaEntity r"
        + " left join StarRatingJpaEntity sr on sr.id = r.starRatingId "
        + "where r.perfumeId = :perfumeId "
        + "order by sr.score")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByScoreAsc(Long perfumeId);

    int countByUserId(Long userId);

}
