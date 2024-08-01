package com.pikachu.purple.infrastructure.elasticsearch.perfume.repository;

import com.pikachu.purple.infrastructure.elasticsearch.perfume.vo.PerfumeDocument;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeElasticSearchRepository extends ElasticsearchRepository<PerfumeDocument, Long> {

    List<PerfumeDocument> findByPerfumeNameOrBrandName(
        String perfumeName,
        String brandName
    );

}
