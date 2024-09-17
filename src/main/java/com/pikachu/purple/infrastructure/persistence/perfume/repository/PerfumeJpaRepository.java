package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeJpaRepository extends JpaRepository<PerfumeJpaEntity, Long> {

    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "where p.brandJpaEntity.name in :brandNames")
    List<PerfumeJpaEntity> findAllByBrandNameIn(List<String> brandNames);

    @Query(value = "SELECT p.* FROM perfume p " +
        "INNER JOIN perfume_note pn ON p.perfume_id = pn.perfume_id " +
        "WHERE pn.note_name IN (" +
        "SELECT upn.note_name FROM user_preference_note upn WHERE upn.user_id = :userId) " +
        "GROUP BY p.perfume_id " +
        "ORDER BY COUNT(pn.perfume_note_id) DESC ",
        nativeQuery = true)
    Page<PerfumeJpaEntity> findByUserPreferenceNotes(@Param("userId") Long userId,
        Pageable pageable);

    @Query(value = "SELECT p.* FROM perfume p WHERE p.perfume_name LIKE :keyword OR p.brand_name LIKE :keyword",
        nativeQuery = true)
    List<PerfumeJpaEntity> findByKeyword(String keyword);

    Optional<PerfumeJpaEntity> findByPerfumeId(Long perfumeId);

}
