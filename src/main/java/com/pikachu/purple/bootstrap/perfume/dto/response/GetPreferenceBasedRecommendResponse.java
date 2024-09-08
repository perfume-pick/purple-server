package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.UserPreferenceNote;
import java.util.List;

public record GetPreferenceBasedRecommendResponse(
    List<UserPreferenceNote> userPreferenceNotes,
    List<Perfume> perfumes
) {

}
