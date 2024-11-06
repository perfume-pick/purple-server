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

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "where sr.perfumeJpaEntity.id = :perfumeId")
    List<StarRatingJpaEntity> findAllByPerfumeId(Long perfumeId);

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "where sr.userJpaEntity.id = :userId")
    List<StarRatingJpaEntity> findAllByUserId(Long userId);

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "left join ReviewJpaEntity re on sr = re.starRatingJpaEntity "
        + "where sr.userJpaEntity.id = :userId "
        + "order by re.likeCount desc")
    List<StarRatingJpaEntity> findAllOrderByLikeCountDesc(Long userId);

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "where sr.userJpaEntity.id = :userId "
        + "order by sr.score desc")
    List<StarRatingJpaEntity> findAllOrderByScoreDesc(Long userId);

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "where sr.userJpaEntity.id = :userId "
        + "order by sr.score asc")
    List<StarRatingJpaEntity> findAllOrderByScoreAsc(Long userId);
}
