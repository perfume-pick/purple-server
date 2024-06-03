package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeJpaRepository extends JpaRepository<PerfumeJpaEntity, Long> {

    @Query(value =
        "SELECT * FROM perfume p INNER JOIN perfume_brand pb ON p.p_brand_name = pb.brand_name WHERE p.p_brand_name IN :brandList",
        nativeQuery = true)
    List<PerfumeJpaEntity> findByBrandNameIn(List<String> brandList);


    @Query(value = "SELECT p.* FROM perfume p " +
        "INNER JOIN perfume_note pn ON p.perfume_id = pn.perfume_id " +
        "WHERE pn.note_name IN (" +
        "SELECT upn.note_name FROM user_preference_note upn WHERE upn.user_id = :userId) " +
        "GROUP BY p.perfume_id " +
        "ORDER BY COUNT(pn.perfume_note_id) DESC ",
        nativeQuery = true)
    Page<PerfumeJpaEntity> findUserPreferenceNotesByUserId(@Param("userId") Long userId, Pageable pageable);

}
