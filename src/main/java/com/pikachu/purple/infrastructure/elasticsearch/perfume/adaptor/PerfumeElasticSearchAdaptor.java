package com.pikachu.purple.infrastructure.elasticsearch.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeDocumentRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.infrastructure.elasticsearch.perfume.vo.PerfumeDocument;
import com.pikachu.purple.infrastructure.elasticsearch.perfume.repository.PerfumeElasticSearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeElasticSearchAdaptor implements PerfumeDocumentRepository {

    private final PerfumeElasticSearchRepository perfumeElasticSearchRepository;

    @Override
    public List<Perfume> findByKeyword(String keyword) {
        List<PerfumeDocument> perfumeDocuments = perfumeElasticSearchRepository.findByPerfumeNameOrBrandName(
            keyword,
            keyword
        );

        return perfumeDocuments.stream()
            .map(PerfumeDocument::toDomain)
            .toList();
    }

    @Override
    public void create(Perfume perfume) {
        PerfumeDocument perfumeDocument = PerfumeDocument.toDocument(perfume);

        perfumeElasticSearchRepository.save(perfumeDocument);
    }

}
