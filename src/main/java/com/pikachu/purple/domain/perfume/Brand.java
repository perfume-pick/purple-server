package com.pikachu.purple.domain.perfume;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Brand {

    private final String name;
    private final String koreanName;
    private final String imageUrl;
    private final int order;

    @Setter
    private List<Perfume> perfumes;

    public Brand(
        String name,
        String koreanName,
        String imageUrl,
        int order
    ) {
        this.name = name;
        this.koreanName = koreanName;
        this.imageUrl = imageUrl;
        this.order = order;
    }

}
