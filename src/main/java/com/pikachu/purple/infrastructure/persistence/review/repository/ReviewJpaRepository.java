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

    @Query("select re "
        + "from ReviewJpaEntity re "
        + "where re.perfumeJpaEntity.id = :perfumeId "
        + "order by re.likeCount desc")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByLikeCountDesc(Long perfumeId);

    @Query("select re "
        + "from ReviewJpaEntity re "
        + "where re.perfumeJpaEntity.id = :perfumeId "
        + "order by re.createdAt desc")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByCreateAtDesc(Long perfumeId);

    @Query("select re "
        + "from ReviewJpaEntity re "
        + "where re.starRatingJpaEntity.perfumeJpaEntity.id = :perfumeId "
        + "order by re.starRatingJpaEntity.score desc")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByScoreDesc(Long perfumeId);

    @Query("select re "
        + "from ReviewJpaEntity re "
        + "where re.starRatingJpaEntity.perfumeJpaEntity.id = :perfumeId "
        + "order by re.starRatingJpaEntity.score")
    List<ReviewJpaEntity> findAllByPerfumeIdOrderByScoreAsc(Long perfumeId);

//    @Query("select r "
//        + "from ReviewJpaEntity r "
//        + "where r.reviewType = :reviewType "
//        + " and FUNCTION('DATE_FORMAT', r.updatedAt, '%Y%m%d') = :updatedDate "
//        + "order by r.id asc")
    List<ReviewJpaEntity> findAllByReviewType(ReviewType reviewType);

    @Query("select r "
        + "from ReviewJpaEntity r "
        + "where r.userJpaEntity.id = :userId and r.perfumeJpaEntity.id = :perfumeId")
    Optional<ReviewJpaEntity> findByUserIdAndPerfumeId(Long userId, Long perfumeId);

    @Query("select count(*) "
        + "from ReviewJpaEntity r "
        + "where r.userJpaEntity.id = :userId")
    int countByUserId(Long userId);

    @Query("select r "
        + "from ReviewJpaEntity r "
        + "where r.userJpaEntity.id = :userId")
    List<ReviewJpaEntity> findAllByUserId(Long userId);

}
