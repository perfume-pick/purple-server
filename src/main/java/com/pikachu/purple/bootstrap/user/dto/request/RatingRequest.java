package com.pikachu.purple.bootstrap.user.dto.request;

import com.pikachu.purple.bootstrap.user.vo.RatingValue;
import java.util.List;

public record RatingRequest(List<RatingValue> ratingValues) {

}
