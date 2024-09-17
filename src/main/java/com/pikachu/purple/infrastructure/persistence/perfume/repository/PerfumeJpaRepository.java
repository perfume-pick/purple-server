package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.domain.user.UserAccord;
import com.pikachu.purple.infrastructure.persistence.accord.AccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeJpaRepository extends JpaRepository<PerfumeJpaEntity, Long> {

    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "where p.brandJpaEntity.name in :brandNames")
    List<PerfumeJpaEntity> findAllByBrandNameIn(List<String> brandNames);


    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "left join PerfumeAccordJpaEntity pa on p = pa.perfumeJpaEntity "
        + "where pa.accordJpaEntity in :accordJpaEntities")
    List<PerfumeJpaEntity> findAllByAccords(List<AccordJpaEntity> accordJpaEntities);


    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "where p.name like :keyword or p.brandJpaEntity.name like :keyword")
    List<PerfumeJpaEntity> findByKeyword(String keyword);

    Optional<PerfumeJpaEntity> findByPerfumeId(Long perfumeId);

}
