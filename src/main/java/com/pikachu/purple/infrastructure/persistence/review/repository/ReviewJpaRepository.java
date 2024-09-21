package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import java.util.List;
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

}
