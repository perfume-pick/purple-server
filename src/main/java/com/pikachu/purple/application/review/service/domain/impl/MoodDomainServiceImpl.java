package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.MoodRepository;
import com.pikachu.purple.application.review.service.domain.MoodDomainService;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoodDomainServiceImpl implements MoodDomainService {

    private final MoodRepository moodRepository;

    @Override
    public List<Mood> findAll() {
        return moodRepository.findAll();
    }

}
