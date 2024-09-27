package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StarRatingJpaRepository extends JpaRepository<StarRatingJpaEntity, Long> {

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "where sr.userJpaEntity.id = :userId and sr.perfumeJpaEntity.id = :perfumeId")
    Optional<StarRatingJpaEntity> findByUserIdAndPerfumeId(Long userId, Long perfumeId);

    List<StarRatingJpaEntity> findAllByUserJpaEntity(UserJpaEntity userJpaEntity);

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "where FUNCTION('DATE_FORMAT', sr.updatedAt, '%Y%m%d') = :updatedDate "
        + "order by sr.perfumeJpaEntity.id asc, sr.score asc")
    List<StarRatingJpaEntity> findAllByUpdatedDate(String updatedDate);
    
}
