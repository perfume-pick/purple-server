package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.bootstrap.user.vo.RatingValue;
import java.util.List;

public interface RatingSaveUseCase {

    void invoke(Command command);

    record Command(
        Long userId,
        List<RatingValue> ratingValueList
    ) {
    }

}
