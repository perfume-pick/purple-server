package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.accord.entity.AccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import java.util.List;
import org.springframework.data.domain.Limit;
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

    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "left join ReviewJpaEntity r on r.perfumeJpaEntity = p "
        + "group by p having count(r) > 0 "
        + "order by count(r) desc")
    List<PerfumeJpaEntity> findAllHavingReviewCountNotZeroOrderByReviewCount(Limit limit);

    @Query("select p.id "
        + "from PerfumeJpaEntity p")
    List<Long> findAllId();

}
