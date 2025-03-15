package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeJpaRepository extends JpaRepository<PerfumeJpaEntity, Long> {

    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "where p.brandJpaEntity.name = :brandName")
    List<PerfumeJpaEntity> findAllByBrandName(String brandName);


    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "left join PerfumeAccordJpaEntity pa on p.id = pa.perfumeId "
        + "where pa.accord in :accords "
        + "group by p "
        + "order by count(pa) desc")
    List<PerfumeJpaEntity> findAllByAccordNames(List<Accord> accords, Limit limit);


    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "where p.name like :keyword"
        + " or p.brandJpaEntity.name like :keyword"
        + " or p.koreanName like :keyword"
        + " or p.brandJpaEntity.koreaName like :keyword")
    List<PerfumeJpaEntity> findAllByKeyword(String keyword);

    @Query("select p "
        + "from PerfumeJpaEntity p "
        + "left join ReviewJpaEntity r on r.perfumeId = p.id "
        + "group by p having count(r) > 0 "
        + "order by count(r) desc")
    List<PerfumeJpaEntity> findAllHavingReviewCountNotZeroOrderByReviewCount(Limit limit);

    @Query("select p.id "
        + "from PerfumeJpaEntity p")
    List<Long> findAllId();

    List<PerfumeJpaEntity> findAllByIdIn(List<Long> perfumeIds);

    @Modifying
    @Query("update PerfumeJpaEntity p "
        + "set p.averageScore = :averageScore "
        + "where p.id = :perfumeId")
    void updateAverageScoreByPerfumeId(Long perfumeId, double averageScore);

}
