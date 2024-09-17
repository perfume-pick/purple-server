package com.pikachu.purple.infrastructure.persistence.user.repository;

import com.pikachu.purple.infrastructure.persistence.user.entity.UserAccordJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccordJpaRepository extends JpaRepository<UserAccordJpaEntity, Long> {

    @Query("select ua "
        + "from UserAccordJpaEntity ua "
        + "where ua.userJpaEntity.id = :userId")
    List<UserAccordJpaEntity> findAllByUserId(Long userId);

}
