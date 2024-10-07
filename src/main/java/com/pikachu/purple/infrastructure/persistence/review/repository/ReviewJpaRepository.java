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

    @Query("SELECT re "
        + "FROM ReviewJpaEntity re "
        + "WHERE re.perfumeJpaEntity.id = :perfumeId "
        + "ORDER BY re.createdAt DESC")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByCreateAtDesc(Long perfumeId);

    @Query("SELECT re "
        + "FROM ReviewJpaEntity re "
        + "WHERE re.starRatingJpaEntity.perfumeJpaEntity.id = :perfumeId "
        + "ORDER BY re.starRatingJpaEntity.score DESC")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByScoreDesc(Long perfumeId);

    @Query("SELECT re "
        + "FROM ReviewJpaEntity re "
        + "WHERE re.starRatingJpaEntity.perfumeJpaEntity.id = :perfumeId "
        + "ORDER BY re.starRatingJpaEntity.score")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByScoreAsc(Long perfumeId);

    @Query("select r "
        + "from ReviewJpaEntity r "
        + "where r.reviewType = :reviewType "
        + " and FUNCTION('DATE_FORMAT', r.updatedAt, '%Y%m%d') = :updatedDate "
        + "order by r.id asc")
    List<ReviewJpaEntity> findAllByReviewTypeAndUpdateDate(ReviewType reviewType, String updatedDate);

    @Query("select r "
        + "from ReviewJpaEntity r "
        + "where r.userJpaEntity.id = :userId and r.perfumeJpaEntity.id = :perfumeId")
    Optional<ReviewJpaEntity> findByUserIdAndPerfumeId(Long userId, Long perfumeId);

}
