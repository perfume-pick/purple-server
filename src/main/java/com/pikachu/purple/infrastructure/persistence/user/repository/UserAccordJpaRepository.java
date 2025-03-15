package com.pikachu.purple.infrastructure.persistence.user.repository;

import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserAccordJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccordJpaRepository extends JpaRepository<UserAccordJpaEntity, Long> {

    Optional<UserAccordJpaEntity> findByUserIdAndAccord(Long userId, Accord accord);

    @Query("select ua "
        + "from UserAccordJpaEntity ua "
        + "where ua.userId = :userId "
        + "and ua.score >= 0 "
        + "order by ua.score desc")
    List<UserAccordJpaEntity> findAllByUserIdOrderByScoreDesc(Long userId, Limit limit);

    @Query("select ua "
        + "from UserAccordJpaEntity ua "
        + "where ua.userId = :userId "
        + "and ua.score < 0 "
        + "order by ua.score asc")
    List<UserAccordJpaEntity> findAllByUserIdOrderByScoreAsc(Long userId, Limit limit);

    List<UserAccordJpaEntity> findAllByUserId(Long userId);

}
