package com.pikachu.purple.infrastructure.elasticsearch.perfume.document;

import com.pikachu.purple.domain.perfume.Perfume;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Document(indexName = "perfume")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeDocument {

    @Id
    @Field(type = FieldType.Long)
    private Long perfumeId;

    @Field(type = FieldType.Text)
    private String perfumeName;

    @Field(type = FieldType.Text)
    private String brandName;

    public static Perfume toDomain(PerfumeDocument perfumeDocument) {
        return Perfume.builder()
            .perfumeId(perfumeDocument.getPerfumeId())
            .perfumeName(perfumeDocument.getPerfumeName())
            .brandName(perfumeDocument.getBrandName())
            .build();
    }

}
