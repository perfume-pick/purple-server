package com.pikachu.purple.domain.perfume;

import lombok.Getter;

@Getter
public class Brand {

    private final String name;
    private final String imageUrl;
    private final int order;

    public Brand(
        String name,
        String imageUrl,
        int order
    ) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.order = order;
    }

}
