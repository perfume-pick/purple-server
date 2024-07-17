package com.pikachu.purple.infrastructure.elasticsearch.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeESRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.infrastructure.elasticsearch.perfume.document.PerfumeDocument;
import com.pikachu.purple.infrastructure.elasticsearch.perfume.repository.PerfumeDocumentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeDocumentAdaptor implements PerfumeESRepository {

    private final PerfumeDocumentRepository perfumeDocumentRepository;

    @Override
    public List<Perfume> findByKeyword(String keyword) {
        List<PerfumeDocument> perfumeDocuments = perfumeDocumentRepository.findByPerfumeNameOrBrandName(keyword, keyword);

        return perfumeDocuments.stream()
            .map(PerfumeDocument::toDomain)
            .collect(Collectors.toList());
    }

}
